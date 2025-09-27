;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lcmd
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lcmd.edn")

(def hc-line-start           ["goto_line_start"])
(def mc-line-start           ["StartOfLine"])
(def hc-line-end             ["goto_line_end", "move_char_right"])
(def mc-line-end             ["EndOfLine"])
(def hc-file-start           ["goto_file_start"])
(def lg-top                  ["gotoTop"])
(def mc-file-start           ["CursorStart"])
(def ze-page-up              ["PageScrollUp"])
(def hc-file-end             ["goto_last_line"])
(def lg-bottom               ["gotoBottom"])
(def mc-file-end             ["CursorEnd"])
(def ze-page-down            ["PageScrollDown"])

(def hc-select-line-start    ["select_mode", "goto_line_start", "MODE"])
(def mc-select-line-start    ["SelectToStartOfLine"])
(def hc-select-line-end      ["select_mode", "goto_line_end", "MODE"])
(def mc-select-line-end      ["SelectToEndOfLine"])
(def hc-select-file-start    ["extend_to_file_start"])
(def mc-select-file-start    ["SelectToStart"])
(def hc-select-file-end      ["extend_to_file_end"])
(def mc-select-file-end      ["SelectToEnd"])

(defn lcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Command Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "goto line start",    :exec hc-line-start}
                    {:program c/mc,    :action "goto line start",    :exec mc-line-start}]}        [c/kc_al       [c/kt_a]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto line end",      :exec hc-line-end}
                    {:program c/mc,    :action "goto line end",      :exec mc-line-end}]}          [c/kc_ar       [c/kt_e]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto file start",    :exec hc-file-start}
                    {:program c/lg,    :action "goto top",           :exec lg-top}
                    {:program c/mc,    :action "goto file start",    :exec mc-file-start}
                    {:program c/ze,    :action "page up",            :exec ze-page-up}]}           [c/kc_au       [c/k_hm]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto file end",      :exec hc-file-end}
                    {:program c/lg,    :action "goto bottom",        :exec lg-bottom}
                    {:program c/mc,    :action "goto file end",      :exec mc-file-end}
                    {:program c/ze,    :action "page down",          :exec ze-page-down}]}         [c/kc_ad       [c/k_ed]       c/term]

    ^{:doc/actions [{:program c/hc,    :action "select line start",  :exec hc-select-line-start}
                    {:program c/mc,    :action "select line start",  :exec mc-select-line-start}]} [c/kcs_al      [c/ko_al]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "select line end",    :exec hc-select-line-end}
                    {:program c/mc,    :action "select line end",    :exec mc-select-line-end}]}   [c/kcs_ar      [c/ko_ar]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "select file start",  :exec hc-select-file-start}
                    {:program c/mc,    :action "select file start",  :exec mc-select-file-start}]} [c/kcs_au      [c/ko_au]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "select file end",    :exec hc-select-file-end}
                    {:program c/mc,    :action "select file end",    :exec mc-select-file-end}]}   [c/kcs_ad      [c/ko_ad]      c/term]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [c/kcp_ob      [c/kc_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_cb      [c/kc_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_sc      [c/kc_sc]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "`::<`"}]}          [c/kcp_qu      [c/ks_sc c/ks_sc c/ks_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_bl      [c/kc_bl]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "' <- '"}]}         [c/kcp_cm      [c/k_sp c/ks_cm c/k_hy c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` -> `"}]}         [c/kcp_pe      [c/k_sp c/k_hy c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "`//! `"}]}         [c/kcp_sl      [c/k_sl c/k_sl c/ks_1 c/k_sp]]

    ^{:doc/actions [{}]}                                                                           [c/kcsp_ob     [c/kcs_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_cb     [c/kcs_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_sc     [c/kcs_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_qu     [c/kcs_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_bl     [c/kcs_bl]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` <<- `"}]}        [c/kcsp_cm     [c/k_sp c/ks_cm c/ks_cm c/k_hy c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ->> `"}]}        [c/kcsp_pe     [c/k_sp c/k_hy c/ks_pe c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "`#!/`"}]}          [c/kcsp_sl     [c/ks_3 c/ks_1 c/k_sl]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [c/kcp_db      [c/kc_db]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_re      [c/kc_re]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` != `"}]}        [c/kcp_rs      [c/k_sp c/ks_1 c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` *= `"}]}        [c/kcp_ro      [c/k_sp c/ks_8 c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` /= `"}]}        [c/kcp_rc      [c/k_sp c/k_sl c/k_eq c/k_sp]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_sp      [c/kc_sp]]

    ^{:doc/actions [{}]}                                                                           [c/kcsp_db     [c/kcs_db]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "`; `"}]}          [c/kcsp_re     [c/k_sp c/ks_sc c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` .!= `"}]}       [c/kcsp_rs     [c/k_sp c/k_pe c/ks_1 c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` .*= `"}]}       [c/kcsp_ro     [c/k_sp c/k_pe c/ks_8 c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",            :sequence "` ./= `"}]}       [c/kcsp_rc     [c/k_sp c/k_pe c/k_sl c/k_eq c/k_sp]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_sp     [c/kcs_sp]]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [c/kcp_1       [c/kc_1]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_2       [c/kc_2]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_3       [c/kc_3]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_4       [c/kc_4]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_5       [c/kc_5]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_6       [c/kc_6]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_7       [c/kc_7]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_8       [c/kc_8]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence ".("}]}                [c/kcp_9       [c/k_pe c/ks_9]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_0       [c/kc_0]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` -= `"}]}            [c/kcp_hy      [c/k_sp c/k_hy c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` += `"}]}            [c/kcp_eq      [c/k_sp c/ks_eq c/k_eq c/k_sp]]

    ^{:doc/actions [{}]}                                                                           [c/kcsp_1      [c/kcs_1]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_2      [c/kcs_2]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_3      [c/kcs_3]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_4      [c/kcs_4]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_5      [c/kcs_5]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_6      [c/kcs_6]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_7      [c/kcs_7]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_8      [c/kcs_8]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "`:(`"}]}              [c/kcsp_9      [c/ks_sc c/ks_9]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_0      [c/kcs_0]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` .-= `"}]}           [c/kcsp_hy     [c/k_sp c/k_pe c/k_hy c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,   :action "terminal",         :sequence "` .+= `"}]}           [c/kcsp_eq     [c/k_sp c/k_pe c/ks_eq c/k_eq c/k_sp]]

    ; alphabetic glyphs
    ^{:doc/actions [{}]}                                                                           [c/kcp_a       [c/kc_a]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_b       [c/kc_b]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_c       [c/kc_c]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_d       [c/kc_d]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_e       [c/kc_e]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_f       [c/kc_f]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_g       [c/kc_g]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_h       [c/kc_h]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_i       [c/kc_i]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_j       [c/kc_j]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_k       [c/kc_k]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_l       [c/kc_l]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_m       [c/kc_m]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_n       [c/kc_n]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_o       [c/kc_o]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_p       [c/kc_p]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_q       [c/kc_q]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_r       [c/kc_r]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_s       [c/kc_s]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_t       [c/kc_t]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_u       [c/kc_u]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_v       [c/kc_v]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_w       [c/kc_w]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_x       [c/kc_x]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_y       [c/kc_y]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_z       [c/kc_z]]
    ^{:doc/actions [{}]}                                                                           [c/kcp_rt      [c/kc_rt]]

    ^{:doc/actions [{}]}                                                                           [c/kcsp_a      [c/kcs_a]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_b      [c/kcs_b]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_c      [c/kcs_c]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_d      [c/kcs_d]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_e      [c/kcs_e]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_f      [c/kcs_f]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_g      [c/kcs_g]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_h      [c/kcs_h]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_i      [c/kcs_i]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_j      [c/kcs_j]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_k      [c/kcs_k]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_l      [c/kcs_l]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_m      [c/kcs_m]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_n      [c/kcs_n]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_o      [c/kcs_o]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_p      [c/kcs_p]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_q      [c/kcs_q]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_r      [c/kcs_r]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_s      [c/kcs_s]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_t      [c/kcs_t]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_u      [c/kcs_u]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_v      [c/kcs_v]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_w      [c/kcs_w]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_x      [c/kcs_x]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_y      [c/kcs_y]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_z      [c/kcs_z]]
    ^{:doc/actions [{}]}                                                                           [c/kcsp_rt     [c/kcs_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
