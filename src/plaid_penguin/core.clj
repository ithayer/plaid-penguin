;; Usage:

;; (ns app.my-ns (:import [app MyThriftClass]))
;;
;; (pp/defthrift MyThriftClass)
;; (pp/thrift-into (MyThriftClass) {:attr "X"})

(ns plaid-penguin.core
  (:require [clojure.contrib.string :as str]))

(defmulti thrift-into (fn [& args#] (class (first args#))))

(defmacro get-field-typemap [cname]
  "Given a classname, returns a map of name -> type for the fields."
  `(into {} (map (fn [f#] [(.getName f#) (.getType f#)]) (.getFields ~cname))))

(defn mk-field-map [field]
  "Constructs a map of name -> field."
  [(.getFieldName field) field])

(defmacro maybe-construct [c v]
  "Given a class, instantiates a new object if it's a Thrift object. Otherwise returns the value."
  `(if (isa? ~c org.apache.thrift.TBase)
     (thrift-into (.newInstance ~c) ~v)
     ~v))

(defmacro defthrift [cname]
  "Construct a defmethod that allows you to do thrift-into."
  `(defmethod thrift-into ~cname [obj# m#]
     (let [field-map# (into {} (map mk-field-map
                                    (keys (eval (symbol (str '~cname "/metaDataMap"))))))
           type-map# (get-field-typemap ~cname)]
       (dorun (map (fn [[k# v#]]
                     (.setFieldValue obj# (field-map# (name k#))
                                     (maybe-construct (type-map# (name k#)) v#))) m#)))
       obj#))
  



  
