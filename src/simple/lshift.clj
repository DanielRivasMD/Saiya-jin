;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; SHIFT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lshift
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

(def out-file "lshift.edn")

(def hc-select-left ["extend_char_left"])
(def hc-select-right ["extend_char_right"])
(def hc-select-up ["extend_line_up"])
(def hc-select-down ["extend_line_down"])
(def hc-delete ["delete_selection_noyank"])
(def hc-delete-word-right ["delete_word_forward"])
(def mc-delete-word-right ["DeleteWordRight"])
(def hc-delete-line-end ["kill_to_line_end"])
(def mc-delete-line-end ["SelectToEndOfLine,Delete"])

(defn lshift []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Shift Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "select prev char",   :exec hc-select-left}]}       [r/ksp_al      [r/ks_al]]
    ^{:doc/actions [{:program c/hc,    :action "select next char",   :exec hc-select-right}]}      [r/ksp_ar      [r/ks_ar]]
    ^{:doc/actions [{:program c/hc,    :action "select line up",     :exec hc-select-up}]}         [r/ksp_au      [r/ks_au]]
    ^{:doc/actions [{:program c/hc,    :action "select line down",   :exec hc-select-down}]}       [r/ksp_ad      [r/ks_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/ksp_ob      [t/ks_ob]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_cb      [t/ks_cb]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_sc      [t/ks_sc]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_qu      [t/ks_qu]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_bl      [t/ks_bl]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_cm      [t/ks_cm]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_pe      [t/ks_pe]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_sl      [t/ks_sl]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [a/ksp_db      [a/ks_db]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_re      [a/ks_re]]
    ^{:doc/actions [{:program c/hc,    :action "delete next char",   :exec hc-delete}]}            [a/ksp_rs      [:delete_forward]]
    ^{:doc/actions [{:program c/hc,    :action "delete next word",   :exec hc-delete-word-right}
                    {:program c/mc,    :action "delete next word",   :exec mc-delete-word-right}]} [a/ksp_ro      [b/ko_d]]
    ^{:doc/actions [{:program c/hc,    :action "delete line start",  :exec hc-delete-line-end}
                    {:program c/mc,    :action "delete line start",  :exec mc-delete-line-end}]}   [a/ksp_rc      [b/kt_k]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_sp      [a/ks_sp]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [n/ksp_1       [n/ks_1]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_2       [n/ks_2]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_3       [n/ks_3]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_4       [n/ks_4]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_5       [n/ks_5]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_6       [n/ks_6]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_7       [n/ks_7]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_8       [n/ks_8]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_9       [n/ks_9]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_0       [n/ks_0]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_hy      [n/ks_hy]]
    ^{:doc/actions [{}]}                                                                           [n/ksp_eq      [n/ks_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [b/ksp_a       [b/ks_a]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_b       [b/ks_b]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_c       [b/ks_c]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_d       [b/ks_d]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_e       [b/ks_e]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_f       [b/ks_f]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_g       [b/ks_g]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_h       [b/ks_h]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_i       [b/ks_i]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_j       [b/ks_j]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_k       [b/ks_k]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_l       [b/ks_l]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_m       [b/ks_m]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_n       [b/ks_n]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_o       [b/ks_o]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_p       [b/ks_p]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_q       [b/ks_q]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_r       [b/ks_r]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_s       [b/ks_s]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_t       [b/ks_t]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_u       [b/ks_u]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_v       [b/ks_v]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_w       [b/ks_w]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_x       [b/ks_x]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_y       [b/ks_y]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_z       [b/ks_z]]
    ^{:doc/actions [{}]}                                                                           [b/ksp_rt      [b/ks_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lshift)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
