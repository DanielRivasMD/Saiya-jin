;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Z
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns mode.z
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

(def out-file "z.edn")

(def hc-last-mod        ["goto_last_modification"])
(def hc-undo            ["undo"])
(def mc-undo            ["Undo"])
(def hc-redo            ["redo"])
(def mc-redo            ["Redo"])
(def hc-prev-diff       ["goto_prev_change"])
(def mc-prev-diff       ["DiffPrevious"])
(def hc-next-diff       ["goto_next_change"])
(def mc-next-diff       ["DiffNext"])
(def hc-prev-diag       ["goto_prev_diag"])
(def hc-next-diag       ["goto_next_diag"])
(def hc-diag-picker     ["diagnostics_picker"])
(def hc-diag-picker-ws  ["workspace_diagnostics_picker"])

(defn z []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Z mode"
   :rules
   [:z-mode
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "undo",               :exec hc-undo}
                    {:program c/mc,    :action "undo",               :exec mc-undo}]}              [r/kp_al       [r/ko_pu]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "redo",               :exec hc-redo}
                    {:program c/mc,    :action "redo",               :exec mc-redo}]}              [r/kp_ar       [r/ko_pd]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "prev change",        :exec hc-prev-diff}
                    {:program c/mc,    :action "prev change",        :exec mc-prev-diff}]}         [r/kp_au       [r/ko_hm]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "next change",        :exec hc-next-diff}
                    {:program c/mc,    :action "next change",        :exec mc-next-diff}]}         [r/kp_ad       [r/ko_ed]       c/term]

    ^{:doc/actions [{}]}                                                                           [r/ksp_al      [r/ks_al]]
    ^{:doc/actions [{}]}                                                                           [r/ksp_ar      [r/ks_ar]]
    ^{:doc/actions [{}]}                                                                           [r/ksp_au      [r/ks_au]]
    ^{:doc/actions [{}]}                                                                           [r/ksp_ad      [r/ks_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [t/kp_ob       [t/k_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kp_cb       [t/k_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kp_sc       [t/k_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kp_qu       [t/k_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kp_bl       [t/k_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kp_cm       [t/k_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kp_pe       [t/k_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kp_sl       [t/k_sl]]

    ^{:doc/actions [{}]}                                                                           [t/ksp_ob      [t/ks_ob]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_cb      [t/ks_cb]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_sc      [t/ks_sc]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_qu      [t/ks_qu]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_bl      [t/ks_bl]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_cm      [t/ks_cm]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_pe      [t/ks_pe]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_sl      [t/ks_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/hc,    :action "goto prev diag",     :exec hc-prev-diag}]}         [a/kp_db       [f/kt_f11]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "diag picker",        :exec hc-diag-picker}]}       [a/kp_re       [f/kt_f13]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto next diag",     :exec hc-next-diag}]}         [a/kp_rs       [f/kt_f12]     c/term]
    ^{:doc/actions [{}]}                                                                           [a/kp_ro       [a/k_ro]]
    ^{:doc/actions [{}]}                                                                           [a/kp_rc       [a/k_rc]]
    ^{:doc/actions [{:program c/hc,    :action "goto last change",   :exec hc-last-mod}]}          [a/kp_sp       [f/kt_f15]     c/term]

    ^{:doc/actions [{}]}                                                                           [a/ksp_db      [a/ks_db]]
    ^{:doc/actions [{:program c/hc,    :action "wspace diag picker", :exec hc-diag-picker-ws}]}    [a/ksp_re      [f/kt_f14]     c/term]
    ^{:doc/actions [{}]}                                                                           [a/ksp_rs      [a/ks_rs]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_ro      [a/ks_ro]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_rc      [a/ks_rc]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_sp      [a/ks_sp]]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [n/kp_1        [n/k_1]]
    ^{:doc/actions [{}]}                                                                           [n/kp_2        [n/k_2]]
    ^{:doc/actions [{}]}                                                                           [n/kp_3        [n/k_3]]
    ^{:doc/actions [{}]}                                                                           [n/kp_4        [n/k_4]]
    ^{:doc/actions [{}]}                                                                           [n/kp_5        [n/k_5]]
    ^{:doc/actions [{}]}                                                                           [n/kp_6        [n/k_6]]
    ^{:doc/actions [{}]}                                                                           [n/kp_7        [n/k_7]]
    ^{:doc/actions [{}]}                                                                           [n/kp_8        [n/k_8]]
    ^{:doc/actions [{}]}                                                                           [n/kp_9        [n/k_9]]
    ^{:doc/actions [{}]}                                                                           [n/kp_0        [n/k_0]]
    ^{:doc/actions [{}]}                                                                           [n/kp_hy       [n/k_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kp_eq       [n/k_eq]]

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

    ; alphabetic glyphs
    ^{:doc/actions [{}]}                                                                           [b/kp_a        [b/k_a]]
    ^{:doc/actions [{}]}                                                                           [b/kp_b        [b/k_b]]
    ^{:doc/actions [{}]}                                                                           [b/kp_c        [b/k_c]]
    ^{:doc/actions [{}]}                                                                           [b/kp_d        [b/k_d]]
    ^{:doc/actions [{}]}                                                                           [b/kp_e        [b/k_e]]
    ^{:doc/actions [{}]}                                                                           [b/kp_f        [b/k_f]]
    ^{:doc/actions [{}]}                                                                           [b/kp_g        [b/k_g]]
    ^{:doc/actions [{}]}                                                                           [b/kp_h        [b/k_h]]
    ^{:doc/actions [{}]}                                                                           [b/kp_i        [b/k_i]]
    ^{:doc/actions [{}]}                                                                           [b/kp_j        [b/k_j]]
    ^{:doc/actions [{}]}                                                                           [b/kp_k        [b/k_k]]
    ^{:doc/actions [{}]}                                                                           [b/kp_l        [b/k_l]]
    ^{:doc/actions [{}]}                                                                           [b/kp_m        [b/k_m]]
    ^{:doc/actions [{}]}                                                                           [b/kp_n        [b/k_n]]
    ^{:doc/actions [{}]}                                                                           [b/kp_o        [b/k_o]]
    ^{:doc/actions [{}]}                                                                           [b/kp_p        [b/k_p]]
    ^{:doc/actions [{}]}                                                                           [b/kp_q        [b/k_q]]
    ^{:doc/actions [{}]}                                                                           [b/kp_r        [b/k_r]]
    ^{:doc/actions [{}]}                                                                           [b/kp_s        [b/k_s]]
    ^{:doc/actions [{}]}                                                                           [b/kp_t        [b/k_t]]
    ^{:doc/actions [{}]}                                                                           [b/kp_u        [b/k_u]]
    ^{:doc/actions [{}]}                                                                           [b/kp_v        [b/k_v]]
    ^{:doc/actions [{}]}                                                                           [b/kp_w        [b/k_w]]
    ^{:doc/actions [{}]}                                                                           [b/kp_x        [b/k_x]]
    ^{:doc/actions [{}]}                                                                           [b/kp_y        [b/k_y]]
    ^{:doc/actions [{}]}                                                                           [b/kp_z        [b/k_z]]
    ^{:doc/actions [{}]}                                                                           [b/kp_rt       [b/k_rt]]

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
      (pp/pprint (z)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
