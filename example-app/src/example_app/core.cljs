
(ns example-app.core
  (:require
   [example-app.app :as app]
   [hyperfiddle.electric :as e]
   [hyperfiddle.electric-dom2 :as dom]))

;; this file must be cljs due to reloading limitions of cljc
;; this complicates the usability of the cli model
;; cli could do more codegen but it feels wrong

;; the below boilerplate could be put into a macro to reduce LOC

(def reactor (atom nil))

(def electric-main
  (e/boot
   (binding [hyperfiddle.electric-dom2/node js/document.body]
     (println "inside boot")
     (app/Root.))))

(defn ^:dev/after-load ^:export start! []
  (assert (nil? @reactor) "reactor already running")
  (reset! reactor (electric-main
                   #(js/console.log "Reactor success:" %)
                   #(js/console.error "Reactor failure:" %))))

(defn ^:dev/before-load stop! []
  (println "stopping")
  (when @reactor (@reactor)) ; teardown
  (reset! reactor nil))

;; (defonce init (start!))
