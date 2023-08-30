(ns electric-cli.init
  (:require [hyperfiddle.electric :as e]
            ;; The namespace being imported is dynamic
            ;; I'm not sure what the state of dynamic require is in cljs
            ;; this module just wraps the startup
            [app]
            [hyperfiddle.electric-dom2 :as dom]))

(defn init [& args]
  ;; can put startup/init here
  (println "INIT CLJS GO"))

(println "starting")

(def electric-main
  (hyperfiddle.electric/boot ; Electric macroexpansion - Clojure to signals compiler
   (binding [hyperfiddle.electric-dom2/node js/document.body]
     ;; app/Root would need to be a convention
     ;; or cli would need to specify it
     ;;
     ;; since dev is assumed here, could also init some dev-only tooling
     (app/Root.))))

(defonce reactor nil)

(defn ^:dev/after-load ^:export start! []
  (assert (nil? reactor) "reactor already running")
  (set! reactor (electric-main
                 #(js/console.log "Reactor success:" %)
                 #(js/console.error "Reactor failure:" %))))

(defn ^:dev/before-load stop! []
  (when reactor (reactor)) ; teardown
  (set! reactor nil))

