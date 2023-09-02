(ns electric-tools.init
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]))

;; (println "insit init" (example-app/Root))
;; (defonce electric-main (atom nil))

;; (defonce reactor nil)

;; (defn ^:dev/after-load ^:export start! []
;;   (println "starting...")
;;   (when (nil? reactor)
;;     (set! reactor (@electric-main
;;                    #(js/console.log "Reactor success:" %)
;;                    #(js/console.error "Reactor failure:" %)))))

;; (defn ^:dev/before-load stop! []
;;   (println "STOP")
;;   (when reactor (reactor)) ; teardown
;;   (set! reactor nil))

;; (defn init-electric! [root-node-sym]
;;   ;; TODO
;;   ;; assert that root-node is a valid electric node
;;   (println "init electric with node" root-node-sym))

;; (defn boot-electric [boot-callback]
;;   (e/boot
;;    (println "starting electric boot")
;;    (binding [hyperfiddle.electric-dom2/node js/document.body]
;;      (boot-callback))))

;; (defn mount-app [boot-callback]
;;   (println "mounting->" boot-callback (type boot-callback))
;;   (when (nil? reactor)
;;     (let [electric-main (boot-electric boot-callback)]
;;       (set! reactor (electric-main
;;                      #(js/console.log "Reactor success:" %)
;;                      #(js/console.error "Reactor failure:" %))))))

;; (init-electric! nil)

