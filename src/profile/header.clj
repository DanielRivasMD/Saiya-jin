;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; HEADER
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; !  | means mandatory
;; #  | means optional
;; C  | left_command
;; T  | left_control
;; O  | left_option
;; S  | left_shift
;; F  | fn
;; Q  | right_command
;; W  | right_control
;; E  | right_option
;; R  | right_shift
;; P  | caps_lock
;; !! | mandatory command + control + optional + shift (hyper)
;; ## | optional any

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns profile.header
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]))

(def out-file "header.edn")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def header
  [{:Default
    {:default true
     :sim     50    ;; simultaneous_threshold_milliseconds (def: 50)
     :delay   300   ;; to_delayed_action_delay_milliseconds (def: 500)
     :alone   300   ;; to_if_alone_timeout_milliseconds (def: 1000)
     :held    500  ;; to_if_held_down_threshold_milliseconds (def: 500)
     }}

   :applications
   {c/browser ["company.thebrowser.Browser"]
    c/finder  ["com.apple.finder"]
    c/mail    ["io.canarymail.mac"]
    c/skim    ["net.sourceforge.skim-app.skim"]
    c/term    ["org.alacritty"]
    c/zoom    ["us.zoom.xos"]}

   :devices
   {:pro    [{:vendor_id 1452 :product_id 832}]
    :air    [{:vendor_id 1452 :product_id 641}]
    :master [{:vendor_id 1133 :product_id 45915}]}

   :templates
   {:launch "osascript -e 'tell application \"%s\" to activate'"}

   :layers
   {:zero-mode      {:key :keypad_num_lock}
    :q-mode         {:key :q}
    :x-mode         {:key :x}
    :z-mode         {:key :z}}])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (doseq [m header]
        (pp/pprint m)
        (println)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
