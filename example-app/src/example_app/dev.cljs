;;
;; this should be in user instead
;;
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