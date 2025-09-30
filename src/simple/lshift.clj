;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; SHIFT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lshift
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

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
    ^{:doc/actions [{:program c/hc,    :action "select prev char",   :exec hc-select-left}]}       [c/ksp_al      [c/ks_al]]
    ^{:doc/actions [{:program c/hc,    :action "select next char",   :exec hc-select-right}]}      [c/ksp_ar      [c/ks_ar]]
    ^{:doc/actions [{:program c/hc,    :action "select line up",     :exec hc-select-up}]}         [c/ksp_au      [c/ks_au]]
    ^{:doc/actions [{:program c/hc,    :action "select line down",   :exec hc-select-down}]}       [c/ksp_ad      [c/ks_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [c/ksp_ob      [c/ks_ob]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_cb      [c/ks_cb]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_sc      [c/ks_sc]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_qu      [c/ks_qu]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_bl      [c/ks_bl]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_cm      [c/ks_cm]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_pe      [c/ks_pe]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_sl      [c/ks_sl]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [c/ksp_db      [c/ks_db]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_re      [c/ks_re]]
    ^{:doc/actions [{:program c/hc,    :action "delete next char", :exec hc-delete}]}              [c/ksp_rs      [:delete_forward]]
    ^{:doc/actions [{:program c/hc,    :action "delete next word", :exec hc-delete-word-right}
                    {:program c/mc,    :action "delete next word", :exec mc-delete-word-right}]}   [c/ksp_ro      [c/ko_d]]
    ^{:doc/actions [{:program c/hc,    :action "delete line start", :exec hc-delete-line-end}
                    {:program c/mc,    :action "delete line start", :exec mc-delete-line-end}]}    [c/ksp_rc      [c/kt_k]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_sp      [c/ks_sp]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [c/ksp_1       [c/ks_1]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_2       [c/ks_2]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_3       [c/ks_3]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_4       [c/ks_4]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_5       [c/ks_5]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_6       [c/ks_6]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_7       [c/ks_7]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_8       [c/ks_8]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_9       [c/ks_9]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_0       [c/ks_0]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_hy      [c/ks_hy]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_eq      [c/ks_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [c/ksp_a       [c/ks_a]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_b       [c/ks_b]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_c       [c/ks_c]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_d       [c/ks_d]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_e       [c/ks_e]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_f       [c/ks_f]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_g       [c/ks_g]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_h       [c/ks_h]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_i       [c/ks_i]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_j       [c/ks_j]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_k       [c/ks_k]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_l       [c/ks_l]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_m       [c/ks_m]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_n       [c/ks_n]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_o       [c/ks_o]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_p       [c/ks_p]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_q       [c/ks_q]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_r       [c/ks_r]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_s       [c/ks_s]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_t       [c/ks_t]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_u       [c/ks_u]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_v       [c/ks_v]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_w       [c/ks_w]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_x       [c/ks_x]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_y       [c/ks_y]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_z       [c/ks_z]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_rt      [c/ks_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lshift)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
