;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; JOKER
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.joker
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]
            [config.arrows :as r]
            [config.technical :as t]
            [config.action :as a]
            [config.numeric :as n]
            [config.alphabetic :as b]
            [config.function :as f]
))

(def out-file "joker.edn")

(defn joker []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Joker Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{}]}                                                                           [r/kotcp_al    [r/kotc_al]]
    ^{:doc/actions [{}]}                                                                           [r/kotcp_ar    [r/kotc_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kotcp_au    [r/kotc_au]]
    ^{:doc/actions [{}]}                                                                           [r/kotcp_ad    [r/kotc_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/kotcp_ob    [t/kotc_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kotcp_cb    [t/kotc_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kotcp_sc    [t/kotc_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kotcp_qu    [t/kotc_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kotcp_bl    [t/kotc_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kotcp_cm    [t/kotc_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kotcp_pe    [t/kotc_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kotcp_sl    [t/kotc_sl]]

   ; action glyphs
    ^{:doc/actions [{:program c/sys,   :action "Control center"}]}                                 [a/kotcp_db    [c/kotc_lock]]
    ^{:doc/actions [{:program c/sys,   :action "Speak"}]}                                          [a/kotcp_re    [a/kotc_re]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Arc"}]}                                     [a/kotcp_rs    [:launch "Arc"]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Alacritty"}]}                               [a/kotcp_ro    [:launch "Alacritty"]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Ghostty"}]}                                 [a/kotcp_rc    [:launch "Ghostty"]]
    ^{:doc/actions [{:program c/sys,   :action "Listen"}]}                                         [a/kotcp_sp    [c/kwp_us]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [n/kotcp_1     [n/kotc_1]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_2     [n/kotc_2]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_3     [n/kotc_3]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_4     [n/kotc_4]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_5     [n/kotc_5]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_6     [n/kotc_6]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_7     [n/kotc_7]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_8     [n/kotc_8]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_9     [n/kotc_9]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_0     [n/kotc_0]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_hy    [n/kotc_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kotcp_eq    [n/kotc_eq]]

; TODO: add keypad binds

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [b/kotcp_a     [b/kotc_a]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_b     [b/kotc_b]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_c     [b/kotc_c]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_d     [b/kotc_d]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_e     [b/kotc_e]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_f     [b/kotc_f]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_g     [b/kotc_g]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_h     [b/kotc_h]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_i     [b/kotc_i]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_j     [b/kotc_j]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_k     [b/kotc_k]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_l     [b/kotc_l]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_m     [b/kotc_m]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_n     [b/kotc_n]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_o     [b/kotc_o]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_p     [b/kotc_p]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_q     [b/kotc_q]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_r     [b/kotc_r]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_s     [b/kotc_s]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_t     [b/kotc_t]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_u     [b/kotc_u]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_v     [b/kotc_v]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_w     [b/kotc_w]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_x     [b/kotc_x]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_y     [b/kotc_y]]
    ^{:doc/actions [{}]}                                                                           [b/kotcp_z     [b/kotc_z]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Notes"}]}                                   [b/kotcp_rt    [:launch "Notes"]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (joker)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
