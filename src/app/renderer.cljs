(ns app.renderer
  (:require [reagent.core :as reagent]))

;; -------------------------
;; Views

(defn main-page []
  [:div
   [:div.titlebar [:h1 "spaceship"]]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [main-page]  (.getElementById js/document "container")))

(defn init []
  (js/console.log "spaceship bridge now coming online...")
  (mount-root))
