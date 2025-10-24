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

(def nu                      [])

(def hc-close-tab            [":buffer-close"])
(def hc-dec                  ["decrement"])
(def hc-file-picker          ["file_picker"])
(def hc-hsplit               ["hsplit"])
(def hc-inc                  ["increment"])
(def hc-last-mod             ["goto_last_modification"])
(def hc-last-move            ["repeat_last_motion"])
(def hc-last-tab             ["goto_last_accessed_file"])
(def hc-lower-case           ["switch_to_lowercase"])
(def hc-next-comm            ["goto_next_comment"])
(def hc-next-diff            ["goto_next_change"])
(def hc-next-move            ["normal_mode", "find_till_char", "MODE"])
(def hc-next-tab             ["goto_next_buffer"])
(def hc-prev-comm            ["goto_prev_comment"])
(def hc-prev-diff            ["goto_prev_change"])
(def hc-prev-move            ["normal_mode", "till_prev_char", "MODE"])
(def hc-prev-tab             ["goto_previous_buffer"])
(def hc-redo                 ["redo"])
(def hc-switch-case          ["switch_case"])
(def hc-undo                 ["undo"])
(def hc-unsplit              ["wonly"])
(def hc-upper-case           ["switch_to_uppercase"])
(def hc-vsplit               ["vsplit"])

(def lg-filter               ["filteringMenu"])
(def lg-recent               ["openRecentRepos"])
(def lg-commit               ["commitChanges"])
(def lg-inc-content          ["increaseContextInDiffView"])
(def lg-dec-content          ["decreaseContextInDiffView"])
(def lg-next-block           ["nextBlock-alt2"])
(def lg-next-tab             ["nextTab"])
(def lg-prev-block           ["prevBlock-alt2"])
(def lg-prev-tab             ["prevTab"])

(def mc-close-tab            ["Quit"])
(def mc-hsplit               ["HSplit"])
(def mc-next-diff            ["DiffNext"])
(def mc-next-tab             ["NextTab"])
(def mc-open-file            ["OpenFile"])
(def mc-prev-diff            ["DiffPrevious"])
(def mc-prev-tab             ["PreviousTab"])
(def mc-redo                 ["Redo"])
(def mc-undo                 ["Undo"])
(def mc-unsplit              ["Unsplit"])
(def mc-vsplit               ["VSplit"])

(defn z-mode []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Z mode"
   :rules
   [:z-mode
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "jump prev buffer",   :exec hc-prev-tab}
                    {:program c/lg,    :action "jump prev tab",      :exec lg-prev-tab}
                    {:program c/mc,    :action "jump prev buffer",   :exec mc-prev-tab}]}          [r/kp_al       [b/kt_b]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump next buffer",   :exec hc-next-tab}
                    {:program c/lg,    :action "jump next tab",      :exec lg-next-tab}
                    {:program c/mc,    :action "jump next buffer",   :exec mc-next-tab}]}          [r/kp_ar       [b/kt_f]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "prev comment",       :exec hc-prev-comm}
                    {:program c/lg,    :action "jump prev block",    :exec lg-prev-block}]}        [r/kp_au       [b/kt_n]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "next comment",       :exec hc-next-comm}
                    {:program c/lg,    :action "jump next block",    :exec lg-next-block}]}        [r/kp_ad       [b/kt_p]       c/term]

    ^{:doc/actions [{:program c/hc,    :action "undo",               :exec hc-undo}
                    {:program c/mc,    :action "undo",               :exec mc-undo}]}              [r/ksp_al      [r/ko_pu]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "redo",               :exec hc-redo}
                    {:program c/mc,    :action "redo",               :exec mc-redo}]}              [r/ksp_ar      [r/ko_pd]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "prev-diff",          :exec hc-prev-diff}
                    {:program c/mc,    :action "prev diff",          :exec mc-prev-diff}]}         [r/ksp_au      [r/ko_hm]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "next-diff",          :exec hc-next-diff}
                    {:program c/mc,    :action "next diff",          :exec mc-next-diff}]}         [r/ksp_ad      [r/ko_ed]       c/term]

    ; technical glyphs
    ^{:doc/actions [{:program c/hc,    :action "decrement number",   :exec hc-dec}]}               [t/kp_ob       [r/kt_hm]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "increment number",   :exec hc-inc}]}               [t/kp_cb       [r/kt_ed]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "lower case",         :exec hc-lower-case}]}        [t/kp_sc       [f/ko_f13]]
    ^{:doc/actions [{:program c/hc,    :action "upper case",         :exec hc-upper-case}]}        [t/kp_qu       [f/ko_f14]]
    ^{:doc/actions [{:program c/hc,    :action "switch case",        :exec hc-switch-case}]}       [t/kp_bl       [f/ko_f15]]
    ^{:doc/actions [{:program c/hc,    :action "move forward",       :exec hc-prev-move}]}         [t/kp_cm       [r/kt_pu]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "move backward",      :exec hc-next-move}]}         [t/kp_pe       [r/kt_pd]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "repeat last move",   :exec hc-last-move}]}         [t/kp_sl       [f/ko_f16]]

    ^{:doc/actions [{}]}                                                                           [t/ksp_ob      [t/ks_ob]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_cb      [t/ks_cb]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_sc      [t/ks_sc]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_qu      [t/ks_qu]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_bl      [t/ks_bl]]
    ; TODO: update old mapping for nushell
    ; ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [t/ksp_pe      [b/ko_x]       c/term]
    ; ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [t/ksp_cm      [b/ko_y]       c/term]
    ; ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [t/ksp_sl      [b/ko_z]       c/term]

    ^{:doc/actions [{}]}                                                                           [t/ksp_cm      [t/ks_cm]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_pe      [t/ks_pe]]
    ^{:doc/actions [{}]}                                                                           [t/ksp_sl      [t/ks_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/hc,    :action "close tab",          :exec hc-close-tab}
                    {:program c/lg,    :action "increase content",   :exec lg-inc-content}
                    {:program c/mc,    :action "close tab",          :exec mc-close-tab}]}         [a/kp_db       [b/kt_g]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "file picker",        :exec hc-file-picker}
                    {:program c/lg,    :action "commit",             :exec lg-commit}
                    {:program c/mc,    :action "open file",          :exec mc-open-file}]}         [a/kp_re       [b/kt_l]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "split right",        :exec hc-vsplit}
                    {:program c/lg,    :action "decrease content",   :exec lg-dec-content}
                    {:program c/mc,    :action "split right",        :exec mc-vsplit}]}            [a/kp_rs       [b/kt_v]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "split down",         :exec hc-hsplit}
                    {:program c/mc,    :action "split down",         :exec mc-hsplit}]}            [a/kp_ro       [b/kt_h]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "close window",       :exec hc-unsplit}
                    {:program c/lg,    :action "filter menu",        :exec lg-filter}
                    {:program c/mc,    :action "close window",       :exec mc-unsplit}]}           [a/kp_rc       [b/kt_j]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "last file",          :exec hc-last-tab}
                    {:program c/lg,    :action "recent",             :exec lg-recent}]}            [a/kp_sp       [b/kt_o]       c/term]

    ^{:doc/actions [{}]}                                                                           [a/ksp_db      [a/ks_db]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_re      [a/ks_re]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_rs      [a/ks_rs]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_ro      [a/ks_ro]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_rc      [a/ks_rc]]
    ^{:doc/actions [{:program c/hc,    :action "last modification",  :exec hc-last-mod}]}          [a/ksp_sp      [f/ko_f17]]

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
      (pp/pprint (z-mode)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
