(ns example-app.app
  (:require
   [hyperfiddle.electric :as e]
   [hyperfiddle.electric-dom2 :as dom]))

#?(:clj (def state (atom [10])))

(e/defn Item []
  (e/client
   (dom/h1 (dom/text "basic ite2m2 "))))

(e/defn Root []

  (e/server
   (let [x (System/currentTimeMillis)]
     (e/client
      (dom/h1 (dom/text "test 0 " x))

      (dom/h2 (dom/text "click to create.")
              (dom/on "click" (e/fn [e]
                                (println "E" e)
                                (e/server
                                 (swap! state conj (rand-int 100))))))

      (e/server
       (e/for [x (e/watch state)]
         (e/client
          (dom/h1 (dom/text x)))))))))