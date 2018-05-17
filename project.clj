(defproject cognicious/bitbucket-oauth-extractor "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]                 
                 [javax.servlet/javax.servlet-api "3.1.0"]
                 [bidi "2.0.16" :exclusions [commons-codec]]
                 [ring-middleware-format "0.7.2"]
                 [aleph "0.4.4"]]
  :main bitbucket-oauth-extractor.core)
