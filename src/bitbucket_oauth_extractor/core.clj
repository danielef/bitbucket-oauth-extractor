(ns bitbucket-oauth-extractor.core
  (:gen-class)
  (:require [aleph.http :as aleph-http]
            [bidi.ring :refer [make-handler]]
            [clojure.string :refer [split]]
            [clojure.java.browse :refer [browse-url]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.util.response :refer [response status]]
            [ring.middleware.format :refer [wrap-restful-format]]))

(defn index-handler [{:keys [params] :as req}]
  (if (empty? params)
    (response "<script>window.location = \"http://localhost:8080?\" + window.location.hash.substr(1); </script>")
    (println params)))

(defn about-handler [req]
  (response {:about :bitbucket-oauth-extractor}))

(defn wrap-cors [handler]
  (fn [request]
    (let [response (handler request)]
      (assoc-in response [:headers "Access-Control-Allow-Origin"] "*"))))

(def app
  (-> (make-handler ["/" {"" index-handler
                          ["about"] about-handler}])
      wrap-keyword-params
      wrap-params
      wrap-cors
      (wrap-restful-format :formats [:json-kw :edn])))

; 

(defn -main
  "I don't do a whole lot."
  [& args]
  (if-let [token (first args)]
    (do
      (aleph-http/start-server app {:port 8080})
      (browse-url (str "https://bitbucket.org/site/oauth2/authorize?client_id=" token "&response_type=token")))))
