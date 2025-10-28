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
    ^{:doc/actions [{}]}                                                                           [r/kotcsp_al   [r/kotcs_al]]
    ^{:doc/actions [{}]}                                                                           [r/kotcsp_ar   [r/kotcs_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kotcsp_au   [r/kotcs_au]]
    ^{:doc/actions [{}]}                                                                           [r/kotcsp_ad   [r/kotcs_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/kotcsp_ob   [t/kotcs_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kotcsp_cb   [t/kotcs_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kotcsp_sc   [t/kotcs_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kotcsp_qu   [t/kotcs_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kotcsp_bl   [t/kotcs_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kotcsp_cm   [t/kotcs_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kotcsp_pe   [t/kotcs_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kotcsp_sl   [t/kotcs_sl]]

   ; action glyphs
    ^{:doc/actions [{:program c/sys,   :action "Control center"}]}                                 [a/kotcsp_db   [c/kotcsp_lock]]
    ^{:doc/actions [{:program c/sys,   :action "Speak"}]}                                          [a/kotcsp_re   [a/kotcs_re]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Arc"}]}                                     [a/kotcsp_rs   [:launch "Arc"]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Alacritty"}]}                               [a/kotcsp_ro   [:launch "Alacritty"]]
    ^{:doc/actions [{:program c/sys,   :action "Launch Ghostty"}]}                                 [a/kotcsp_rc   [:launch "Ghostty"]]
    ^{:doc/actions [{:program c/sys,   :action "Listen"}]}                                         [a/kotcsp_sp   [c/kwp_us]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [n/kotcsp_1    [n/kotcs_1]]
    ^{:doc/actions [{}]}                                                                           [n/kotcsp_2    [n/kotcs_2]]
    ^{:doc/actions [{}]}                                                                           [n/kotcsp_3    [n/kotcs_3]]
    ^{:doc/actions [{}]}                                                                           [n/kotcsp_4    [n/kotcs_4]]
    ^{:doc/actions [{}]}                                                                           [n/kotcsp_5    [n/kotcs_5]]
    ^{:doc/actions [{}]}                                                                           [n/kotcsp_6    [n/kotcs_6]]
    ^{:doc/actions [{}]}                                                                           [n/kotcsp_7    [n/kotcs_7]]
    ^{:doc/actions [{}]}                                                                           [n/kotcsp_8    [n/kotcs_8]]
    ^{:doc/actions [{}]}                                                                           [n/kotcsp_9    [n/kotcs_9]]
    ^{:doc/actions [{}]}                                                                           [n/kotcsp_0    [n/kotcs_0]]
    ^{:doc/actions [{}]}                                                                           [n/kotcsp_hy   [n/kotcs_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kotcsp_eq   [n/kotcs_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_a    [b/kotcs_a]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_b    [b/kotcs_b]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_c    [b/kotcs_c]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_d    [b/kotcs_d]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_e    [b/kotcs_e]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_f    [b/kotcs_f]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_g    [b/kotcs_g]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_h    [b/kotcs_h]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_i    [b/kotcs_i]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_j    [b/kotcs_j]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_k    [b/kotcs_k]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_l    [b/kotcs_l]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_m    [b/kotcs_m]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_n    [b/kotcs_n]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_o    [b/kotcs_o]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_p    [b/kotcs_p]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_q    [b/kotcs_q]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_r    [b/kotcs_r]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_s    [b/kotcs_s]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_t    [b/kotcs_t]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_u    [b/kotcs_u]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_v    [b/kotcs_v]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_w    [b/kotcs_w]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_x    [b/kotcs_x]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_y    [b/kotcs_y]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_z    [b/kotcs_z]]
    ^{:doc/actions [{}]}                                                                           [b/kotcsp_rt   [b/kotcs_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (joker)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
