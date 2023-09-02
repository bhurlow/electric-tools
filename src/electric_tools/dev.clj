(ns electric-tools.dev
  (:require [electric-cli.server :as server]
            [shadow.cljs.devtools.api :as shadow-api]
            [shadow.cljs.devtools.server :as shadow-server]))

;; this is the specific build for use with (watch*)
;; because we want to programatically define it instead of read shadow-cljs
(defn build-config [main-ns]
  {:target :browser
   :devtools {:watch-dir "resources/public" ; live reload CSS
              :hud #{:errors :progress}
              :ignore-warnings true} ; warnings don't prevent hot-reload
    ;; could we put this in /tmp for dev?
   :output-dir "resources/public/js"
   :asset-path "/js"
   :modules {:main {:entries [main-ns]
                    :init-fn (symbol (str main-ns "/start!"))
                    }}
   :build-hooks '[(shadow.cljs.build-report/hook {:output-to "target/build_report.html"})]})

(def electric-server-config
  {:host "0.0.0.0"
   :port 8080
   :resources-path "public"})

(defn electric-dev [{:keys [main-ns]}]
  (shadow-server/start!) ;; not seeing how index.html is served by this
  (shadow-api/watch* (assoc (build-config main-ns) :build-id :dev) {})
  (server/start-server! electric-server-config))

