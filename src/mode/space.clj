;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; SPACE
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns mode.space
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]
            [config.arrows :as r]
            [config.technical :as t]
            [config.action :as a]
            [config.numeric :as n]
            [config.alphabetic :as b]
						))

(def out-file "space.edn")

(def hc-prev-move       ["normal_mode", "till_prev_char", "MODE"])
(def hc-next-move       ["normal_mode", "find_till_char", "MODE"])
; (def hc-last-move       ["repeat_last_motion"])
(def hc-prev-comm       ["goto_prev_comment"])
(def hc-next-comm       ["goto_next_comment"])
; (def hc-rn-symbol       ["rename_symbol"])
; (def hc-symbol-picker   ["symbol_picker"])
; (def hc-go-ref          ["goto_reference"])
; (def hc-go-def          ["goto_definition"])
; (def hc-go-impl         ["goto_implementation"])
; (def hc-go-type-def     ["goto_type_definition"])
; (def hc-split-def       ["vsplit", "goto_definition"])
; (def hc-split-type-def  ["vsplit", "goto_type_definition"])

(defn space []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Space mode"
   :rules
   [:space-mode
    ; arrow glyphs
    ^{:doc/actions [{:program, c/hc    :action "find char forward",  :exec hc-prev-move}]}         [r/kp_al       [r/kt_pu]      c/term]
    ^{:doc/actions [{:program, c/hc    :action "find char backward", :exec hc-next-move}]}         [r/kp_ar       [r/kt_pd]      c/term]
    ; ^{:doc/actions [{:program, c/hc    :action "repeat last motion", :exec hc-last-move}]}         [r/kp_sp       [:f18]         c/term]
    ^{:doc/actions [{:program, c/hc    :action "goto prev comment",  :exec hc-prev-comm}]}         [r/kp_au       [r/kt_hm]      c/term]
    ^{:doc/actions [{:program, c/hc    :action "goto next comment",  :exec hc-next-comm}]}         [r/kp_ad       [r/kt_ed]      c/term]

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
    ^{:doc/actions [{}]}                                                                           [a/kp_db       [a/k_db]]
    ; ^{:doc/actions [{:program, c/hc    :action "symbol picker",      :exec hc-symbol-picker}]}     [a/kp_re       [:!TSf13]      c/term]
    ; ^{:doc/actions [{:program, c/hc    :action "goto def",           :exec hc-split-def}]}         [a/kp_rs       [:!Tf18]       c/term]
    ; ^{:doc/actions [{:program, c/hc    :action "goto type def",      :exec hc-split-type-def}]}    [a/kp_ro       [:!Tf19]       c/term]
    ; ^{:doc/actions [{}]}                                                                           [a/kp_rc       [a/k_rc]]
    ^{:doc/actions [{}]}                                                                           [a/kp_sp       [a/k_sp]]

    ^{:doc/actions [{}]}                                                                           [a/ksp_db      [a/ks_db]]
    ^{:doc/actions [{}]}                                                                           [a/ksp_re      [a/ks_re]]
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
    ; ^{:doc/actions [{:program, c/hc    :action "goto def",           :exec hc-go-def}]}            [b/kp_d        [:!Tf17]       c/term]
    ^{:doc/actions [{}]}                                                                           [b/kp_e        [b/k_e]]
    ^{:doc/actions [{}]}                                                                           [b/kp_f        [b/k_f]]
    ^{:doc/actions [{}]}                                                                           [b/kp_g        [b/k_g]]
    ^{:doc/actions [{}]}                                                                           [b/kp_h        [b/k_h]]
    ; ^{:doc/actions [{:program, c/hc    :action "goto impl",          :exec hc-go-impl}]}           [b/kp_i        [:!TSf16]      c/term]
    ^{:doc/actions [{}]}                                                                           [b/kp_j        [b/k_j]]
    ^{:doc/actions [{}]}                                                                           [b/kp_k        [b/k_k]]
    ^{:doc/actions [{}]}                                                                           [b/kp_l        [b/k_l]]
    ^{:doc/actions [{}]}                                                                           [b/kp_m        [b/k_m]]
    ; ^{:doc/actions [{:program, c/hc    :action "rename symbol",      :exec hc-rn-symbol}]}         [b/kp_n        [:!TSf11]      c/term]
    ^{:doc/actions [{}]}                                                                           [b/kp_o        [b/k_o]]
    ^{:doc/actions [{}]}                                                                           [b/kp_p        [b/k_p]]
    ^{:doc/actions [{}]}                                                                           [b/kp_q        [b/k_q]]
    ; ^{:doc/actions [{:program, c/hc    :action "goto ref",           :exec hc-go-ref}]}            [b/kp_r        [:!Tf16]       c/term]
    ^{:doc/actions [{}]}                                                                           [b/kp_s        [b/k_s]]
    ; ^{:doc/actions [{:program, c/hc    :action "goto type def",      :exec hc-go-type-def}]}       [b/kp_t        [:!TSf17]      c/term]
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
      (pp/pprint (space)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

