# spaceship-bridge

## description

this is a companion interface to
[spaceship](https://github.com/du5t/spaceship/). the goal is to help
contextualise the work there and enable graphical usage.

## how to run

### dev mode

you'll need [lein](http://leiningen.org/#install) and
[boot](https://github.com/boot-clj/boot) for a development environment, as well
as [electron-prebuilt](https://github.com/mafintosh/electron-prebuilt) or
something like it.

once you've sorted those, run `boot watch dev-build` in the repo root directory,
and while you've got that running, run `electron target/`.

for console REPL access, run `boot repl -c` from the repo root directory, then
eval `(start-repl)` once you've got a prompt.

### production

according to
[martin klepsch](https://github.com/martinklepsch/electron-and-clojurescript) on
whose system this project is based, the way to package up an electron app is
with [`electron-packager`](https://github.com/maxogden/electron-packager):

```
electron-packager target/ spaceship-bridge --platform=darwin --arch=x64 --version=0.31.2
```

packaging up the whole ship, engines and all, is still in the works.
