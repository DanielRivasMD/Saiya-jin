;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; KEYBOARDS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.keyboards
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]))

(def out-file "keyboards.edn")

(defn keyboards []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Additional keys - MacPro"
   :rules
   [;
    ; keys - MacPro
    [:#Pdelete_or_backspace [:grave_accent_and_tilde] :pro]                                               ; '`'
    [:!C#Pdelete_or_backspace [:!Sgrave_accent_and_tilde] :pro]                                           ; '~'
    [:!O#Pdelete_or_backspace [:spacebar :!Sgrave_accent_and_tilde :spacebar] :pro]                       ; ' ~ '

    ; keys - MacAir
    [:#Pdelete_or_backspace [:grave_accent_and_tilde] :air]                                               ; '`'
    [:!T#Pdelete_or_backspace [:grave_accent_and_tilde :grave_accent_and_tilde :grave_accent_and_tilde] :air]  ; '```'
    [:!S#Pdelete_or_backspace [:!Sgrave_accent_and_tilde] :air]                                           ; '~'
    [:!O#Pdelete_or_backspace [:spacebar :!Sgrave_accent_and_tilde :spacebar] :air]                       ; ' ~ '
    [:!C#Pdelete_or_backspace [:spacebar :equal_sign :!Sgrave_accent_and_tilde :spacebar] :air]          ; ' =~ '
    [:f1 :display_brightness_decrement :air]
    [:f2 :display_brightness_increment :air]
    [:f3 :illumination_decrement :air]
    [:f4 :illumination_increment :air]
    [:f10 :mute :air]
    [:f11 :volume_decrement :air]
    [:f12 :volume_increment :air]

    ; keys - Master
    [:#Pdelete_or_backspace [:grave_accent_and_tilde] :master]                                               ; '`'
    [:!S#Pdelete_or_backspace [:!Sgrave_accent_and_tilde] :master]                                           ; '~'
    [:!O#Pdelete_or_backspace [:spacebar :!Sgrave_accent_and_tilde :spacebar] :master]                       ; ' ~ '

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
