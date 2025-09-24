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

; BUG: identify! issue resides on the brackets closing up as a unit over Default, to resolve, segregate each block & join them by echoed text
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns profile.header
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "header.edn")

(def header
[
  {:Default
   {:default true
    :sim     50    ;; simultaneous_threshold_milliseconds (def: 50)
    :delay   300   ;; to_delayed_action_delay_milliseconds (def: 500)
    :alone   300   ;; to_if_alone_timeout_milliseconds (def: 1000)
    :held    500}  ;; to_if_held_down_threshold_milliseconds (def: 500)
}

   {:applications
   {:browser ["company.thebrowser.Browser"]
    :finder  ["com.apple.finder"]
    :mail    ["io.canarymail.mac"]
    :skim    ["net.sourceforge.skim-app.skim"]
    :term    ["org.alacritty"]
    :zoom    ["us.zoom.xos"]}
}

{:devices
   {:pro    [{:vendor_id 1452 :product_id 832}]
    :air    [{:vendor_id 1452 :product_id 641}]
    :master [{:vendor_id 1133 :product_id 45915}]}
}

   {:templates
   {:launch "osascript -e 'tell application \"%s\" to activate'"}
}
   {:layers
   {:zero-mode      {:key :keypad_num_lock}
    :q-mode         {:key :q}
    :w-mode         {:key :w}
    :j-mode         {:key :j}
    :k-mode         {:key :k}
    :z-mode         {:key :z}
    :x-mode         {:key :x}
    :obracket-mode  {:key :open_bracket}   ;; '[' + letter = rust snippet
    :cbracket-mode  {:key :close_bracket}  ;; ']' + letter = go snippet
    :semicolon-mode {:key :semicolon}      ;; ';' + letter = umlaut
    :quote-mode     {:key :quote}          ;; ''' + letter = circumflex
    :backslash-mode {:key :backslash}      ;; '\' + letter = grave accent
    :comma-mode     {:key :comma}
    :period-mode    {:key :period}
    :slash-mode     {:key :slash}}}])

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
