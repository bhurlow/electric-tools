# electric-tools

`electric-tools` is a package of namespaces for developing and building [electric](https://github.com/hyperfiddle/electric) apps. 

### Development

`electric-tools` bundles the cljs and server config required to develop an electric app, without needing to clone the [electric starter app](https://github.com/hyperfiddle/electric-starter-app/). To use it, you first need to install an alias like:

```clojure
{;; resources must be included on the classpath becaue electric
 ;; generates a manifest.edn
 :paths ["src" "dev" "resources"]
 :deps {io.github.bhurlow/electric-tools {:git/sha "2517cfa"}}
 :aliases {:dev {:ns-default electric-tools.dev
                 :jvm-opts ["-Xss2m"]}}}
```

Next, create a dev clojurescript namespace which imports your electric app index. Note this file _must_ be clojurescript (.cljs not .cljc) due to reloading specifics

```clojure
(ns example-app.dev
  (:require
   [example-app.app :as app] ;; require your electric app main ns 
   [hyperfiddle.electric :as e]
   [hyperfiddle.electric-dom2 :as dom]))

(defonce reactor nil)

(def electric-main
  (e/boot
   (binding [hyperfiddle.electric-dom2/node js/document.body]
     (println "inside boot")
     (app/Root.))))

(defn ^:dev/after-load ^:export start! []
  (assert (nil? @reactor) "reactor already running")
  (set! reactor (electric-main
                 #(js/console.log "Reactor success:" %)
                 #(js/console.error "Reactor failure:" %))))

(defn ^:dev/before-load stop! []
  (when reactor (@reactor))
  (reset! reactor nil))
```

Finally, you can start the dev toolchain using 

```sh
clojure -X:dev electric-dev :main-ns example-app.dev
```

where `main-ns` is the namespace of the initialization code from the previous step 

### Buliding

Built electric apps are just java jar files which include the built client code, server and user code. To build with `electric-tools` TODO


