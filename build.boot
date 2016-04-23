(set-env!
 :source-paths    #{"sass" "src"}
 :resource-paths  #{"resources"}
 :dependencies '[[adzerk/boot-cljs "1.7.48-3" :scope "test"]
                 [org.clojure/clojurescript "1.7.107"]
                 [adzerk/boot-cljs-repl "0.1.10-SNAPSHOT" :scope "test"]
                 [adzerk/boot-reload    "0.3.2-SNAPSHOT"  :scope "test"]
                 [com.cemerick/piggieback "0.2.1" :scope "test"]
                 [weasel "0.7.0" :scope "test"]
                 [org.clojure/tools.nrepl "0.2.12" :scope "test"]
                 [reagent "0.5.1"]
                 [mathias/boot-sassc  "0.1.1" :scope "test"]
                 [secretary "1.2.3"]
                 [timothypratley/reanimated "0.1.4"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload    :refer [reload]]
 '[mathias.boot-sassc  :refer [sass]])

(deftask prod-build []
  (comp (cljs :ids #{"main"}
              :optimizations :simple)
        (cljs :ids #{"renderer"}
              :optimizations :advanced)
        (sass :output-dir "css")))

(deftask dev-build []
  (comp ;; Audio feedback about warnings etc. =======================
        ;; (speak)
        ;; Inject REPL and reloading code into renderer build =======
        (cljs-repl :ids #{"renderer"})
        (reload    :ids #{"renderer"}
                   :on-jsload 'app.renderer/init)
        ;; Compile renderer =========================================
        (cljs      :ids #{"renderer"})
        ;; Compile JS for main process ==============================
        ;; path.resolve(".") which is used in CLJS's node shim
        ;; returns the directory `electron` was invoked in and
        ;; not the directory our main.js file is in.
        ;; Because of this we need to override the compilers `:asset-path option`
        ;; See http://dev.clojure.org/jira/browse/CLJS-1444 for details.
        (cljs      :ids #{"main"}
                   :compiler-options {:asset-path "target/main.out"
                                      :closure-defines {'app.main/dev? true}})
        (sass :output-dir "css")))
