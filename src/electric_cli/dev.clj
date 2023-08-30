(ns electric-cli.dev
  (:require [clojure.tools.build.api :as b]
            [electric-cli.server :as server]
            ;; [org.corfield.build :as bb]
            [shadow.cljs.devtools.api :as shadow-api] ; so as not to shell out to NPM for shadow
            [shadow.cljs.devtools.server :as shadow-server]))

(def version (b/git-process {:git-args "describe --tags --long --always --dirty"}))

;; this is the specific build for use with (watch*)
;; because we want to programatically define it instead of read shadow-cljs
(def build-config
  '{:target :browser
    :devtools {:watch-dir "resources/public" ; live reload CSS
               :hud #{:errors :progress}
               :ignore-warnings true} ; warnings don't prevent hot-reload
    ;; could we put this in /tmp for dev?
    :output-dir "resources/public/js"
    :asset-path "/js"
    ;; the current input code on the classpath needs to be included here
    ;; hopefully we don't have to codegen something
    :modules {:main {:entries [electric-cli.init]
                     :init-fn electric-cli.init/init}}
    :build-hooks [(shadow.cljs.build-report/hook {:output-to "target/build_report.html"})
                      ;; (user/rcf-shadow-hook)
                  ]})

(def electric-server-config
  {:host "0.0.0.0", :port 8080, :resources-path "public"})

(defn dev [{:keys [main-ns] :as args}]
  (println "starting dev mode" args)

  (shadow-server/start!) ;; not seeing how index.html is served by this
  (shadow-api/watch* (assoc build-config :build-id :dev) {})
  (server/start-server! electric-server-config)
  
  
  )

