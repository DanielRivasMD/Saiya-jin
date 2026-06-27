;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; KEYBOARDS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.keyboards
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]
            [config.arrows :as r]
            [config.technical :as t]
            [config.action :as a]
            [config.numeric :as n]
            [config.alphabetic :as b]
            [config.function :as f]

            [config.helix :as hx]
            [config.lazygit :as lg]
            [config.micro :as mc]
            [config.serpl :as sr]
            [config.zellij :as zj]
))

(def out-file "keyboards.edn")

(defn keyboards []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  ; TODO: update documentation
  {:des "Additional keys"
   :rules
   [;
    ; keys - MacPro
    ^{:doc/actions [{}]}                                                                           [a/k_db        [c/k_grave]    :pro]

    ; keys - MacAir
    ^{:doc/actions [{}]}                                                                           [a/k_db        [c/k_grave]    :air]
    [:f1 :display_brightness_decrement :air]
    [:f2 :display_brightness_increment :air]
    [:f3 :illumination_decrement :air]
    [:f4 :illumination_increment :air]
    [:f10 :mute :air]
    [:f11 :volume_decrement :air]
    [:f12 :volume_increment :air]

    ; keys - Master
    ^{:doc/actions [{}]}                                                                           [a/k_db        [c/k_grave]    :master]

    ; keypad
    [:#Pkeypad_hyphen :mute]
    [:#Pkeypad_plus :volume_increment]
    [:#Pkeypad_enter :volume_decrement]

    [:!S#Pkeypad_hyphen :keypad_hyphen]
    [:!S#Pkeypad_plus :keypad_plus]
    [:!S#Pkeypad_enter :equal_sign]
    [:!Sf18 :keypad_slash]
    [:!Sf19 :keypad_asterisk]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (keyboards)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
