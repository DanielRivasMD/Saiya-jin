;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; SPACE
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns mode.space
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

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
    ^{:doc/actions [{:program, c/hc    :action "find char forward",  :exec hc-prev-move}]}         [c/kp_al       [c/kt_pu]      c/term]
    ^{:doc/actions [{:program, c/hc    :action "find char backward", :exec hc-next-move}]}         [c/kp_ar       [c/kt_pd]      c/term]
    ; ^{:doc/actions [{:program, c/hc    :action "repeat last motion", :exec hc-last-move}]}         [c/kp_sp       [:f18]         c/term]
    ^{:doc/actions [{:program, c/hc    :action "goto prev comment",  :exec hc-prev-comm}]}         [c/kp_au       [c/kt_hm]      c/term]
    ^{:doc/actions [{:program, c/hc    :action "goto next comment",  :exec hc-next-comm}]}         [c/kp_ad       [c/kt_ed]      c/term]

    ^{:doc/actions [{}]}                                                                           [c/ksp_al      [c/ks_al]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_ar      [c/ks_ar]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_au      [c/ks_au]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_ad      [c/ks_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [c/kp_ob       [c/k_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kp_cb       [c/k_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kp_sc       [c/k_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kp_qu       [c/k_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kp_bl       [c/k_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kp_cm       [c/k_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kp_pe       [c/k_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kp_sl       [c/k_sl]]

    ^{:doc/actions [{}]}                                                                           [c/ksp_ob      [c/ks_ob]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_cb      [c/ks_cb]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_sc      [c/ks_sc]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_qu      [c/ks_qu]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_bl      [c/ks_bl]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_cm      [c/ks_cm]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_pe      [c/ks_pe]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_sl      [c/ks_sl]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [c/kp_db       [c/k_db]]
    ; ^{:doc/actions [{:program, c/hc    :action "symbol picker",      :exec hc-symbol-picker}]}     [c/kp_re       [:!TSf13]      c/term]
    ; ^{:doc/actions [{:program, c/hc    :action "goto def",           :exec hc-split-def}]}         [c/kp_rs       [:!Tf18]       c/term]
    ; ^{:doc/actions [{:program, c/hc    :action "goto type def",      :exec hc-split-type-def}]}    [c/kp_ro       [:!Tf19]       c/term]
    ; ^{:doc/actions [{}]}                                                                           [c/kp_rc       [c/k_rc]]
    ^{:doc/actions [{}]}                                                                           [c/kp_sp       [c/k_sp]]

    ^{:doc/actions [{}]}                                                                           [c/ksp_db      [c/ks_db]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_re      [c/ks_re]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_rs      [c/ks_rs]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_ro      [c/ks_ro]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_rc      [c/ks_rc]]
    ^{:doc/actions [{}]}                                                                           [c/ksp_sp      [c/ks_sp]]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [c/kp_1        [c/k_1]]
    ^{:doc/actions [{}]}                                                                           [c/kp_2        [c/k_2]]
    ^{:doc/actions [{}]}                                                                           [c/kp_3        [c/k_3]]
    ^{:doc/actions [{}]}                                                                           [c/kp_4        [c/k_4]]
    ^{:doc/actions [{}]}                                                                           [c/kp_5        [c/k_5]]
    ^{:doc/actions [{}]}                                                                           [c/kp_6        [c/k_6]]
    ^{:doc/actions [{}]}                                                                           [c/kp_7        [c/k_7]]
    ^{:doc/actions [{}]}                                                                           [c/kp_8        [c/k_8]]
    ^{:doc/actions [{}]}                                                                           [c/kp_9        [c/k_9]]
    ^{:doc/actions [{}]}                                                                           [c/kp_0        [c/k_0]]
    ^{:doc/actions [{}]}                                                                           [c/kp_hy       [c/k_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kp_eq       [c/k_eq]]

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

    ; alphabetic glyphs
    ^{:doc/actions [{}]}                                                                           [c/kp_a        [c/k_a]]
    ^{:doc/actions [{}]}                                                                           [c/kp_b        [c/k_b]]
    ^{:doc/actions [{}]}                                                                           [c/kp_c        [c/k_c]]
    ; ^{:doc/actions [{:program, c/hc    :action "goto def",           :exec hc-go-def}]}            [c/kp_d        [:!Tf17]       c/term]
    ^{:doc/actions [{}]}                                                                           [c/kp_e        [c/k_e]]
    ^{:doc/actions [{}]}                                                                           [c/kp_f        [c/k_f]]
    ^{:doc/actions [{}]}                                                                           [c/kp_g        [c/k_g]]
    ^{:doc/actions [{}]}                                                                           [c/kp_h        [c/k_h]]
    ; ^{:doc/actions [{:program, c/hc    :action "goto impl",          :exec hc-go-impl}]}           [c/kp_i        [:!TSf16]      c/term]
    ^{:doc/actions [{}]}                                                                           [c/kp_j        [c/k_j]]
    ^{:doc/actions [{}]}                                                                           [c/kp_k        [c/k_k]]
    ^{:doc/actions [{}]}                                                                           [c/kp_l        [c/k_l]]
    ^{:doc/actions [{}]}                                                                           [c/kp_m        [c/k_m]]
    ; ^{:doc/actions [{:program, c/hc    :action "rename symbol",      :exec hc-rn-symbol}]}         [c/kp_n        [:!TSf11]      c/term]
    ^{:doc/actions [{}]}                                                                           [c/kp_o        [c/k_o]]
    ^{:doc/actions [{}]}                                                                           [c/kp_p        [c/k_p]]
    ^{:doc/actions [{}]}                                                                           [c/kp_q        [c/k_q]]
    ; ^{:doc/actions [{:program, c/hc    :action "goto ref",           :exec hc-go-ref}]}            [c/kp_r        [:!Tf16]       c/term]
    ^{:doc/actions [{}]}                                                                           [c/kp_s        [c/k_s]]
    ; ^{:doc/actions [{:program, c/hc    :action "goto type def",      :exec hc-go-type-def}]}       [c/kp_t        [:!TSf17]      c/term]
    ^{:doc/actions [{}]}                                                                           [c/kp_u        [c/k_u]]
    ^{:doc/actions [{}]}                                                                           [c/kp_v        [c/k_v]]
    ^{:doc/actions [{}]}                                                                           [c/kp_w        [c/k_w]]
    ^{:doc/actions [{}]}                                                                           [c/kp_x        [c/k_x]]
    ^{:doc/actions [{}]}                                                                           [c/kp_y        [c/k_y]]
    ^{:doc/actions [{}]}                                                                           [c/kp_z        [c/k_z]]
    ^{:doc/actions [{}]}                                                                           [c/kp_rt       [c/k_rt]]

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
      (pp/pprint (space)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

