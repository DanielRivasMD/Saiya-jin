;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CTL-CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lctlcmd
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lctlcmd.edn")

(def hc-prev-tab             ["goto_previous_buffer"])
(def lg-prev-tab             ["prevTab"])
(def mc-prev-tab             ["PreviousTab"])
(def ze-move-left            ["MovePane \"Left\";"])
(def hc-next-tab             ["goto_next_buffer"])
(def lg-next-tab             ["nextTab"])
(def mc-next-tab             ["NextTab"])
(def ze-move-rigth           ["MovePane \"Right\";"])
(def hc-inc                  ["increment"])
(def lg-prev-block           ["prevBlock-alt2"])
(def ze-move-up              ["MovePane \"Up\";"])
(def hc-dec                  ["decrement"])
(def lg-next-block           ["nextBlock-alt2"])
(def ze-move-down            ["MovePane \"Down\";"])
(def hc-close-tab            [":buffer-close"])
(def mc-close-tab            ["Quit"])
(def hc-unsplit              ["wonly"])
(def mc-unsplit              ["Unsplit"])
(def hc-vsplit               ["vsplit"])
(def mc-vsplit               ["VSplit"])
(def hc-hsplit               ["hsplit"])
(def mc-hsplit               ["HSplit"])
(def hc-close-split          ["wclose"])
(def mc-close-split          ["Unsplit"])
(def hc-last-tab             ["goto_last_accessed_file"])
(def br-toggle-preview       [])

(defn lctlcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Control - Command Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "jump prev buffer",   :exec hc-prev-tab}
                    {:program c/lg,    :action "jump prev tab",      :exec lg-prev-tab}
                    {:program c/mc,    :action "jump prev buffer",   :exec mc-prev-tab}
                    {:program c/ze,    :action "move left",          :exec ze-move-left}]}         [c/ktcp_al     [c/kt_b]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "jump next buffer",   :exec hc-next-tab}
                    {:program c/lg,    :action "jump next tab",      :exec lg-next-tab}
                    {:program c/mc,    :action "jump next buffer",   :exec mc-next-tab}
                    {:program c/ze,    :action "move right",         :exec ze-move-rigth}]}        [c/ktcp_ar     [c/kt_f]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "increment number",   :exec hc-inc}
                    {:program c/lg,    :action "jump prev block",    :exec lg-prev-block}
                    {:program c/ze,    :action "move up",            :exec ze-move-up}]}           [c/ktcp_au     [c/kt_n]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "decrement number",   :exec hc-dec}
                    {:program c/lg,    :action "jump next block",    :exec lg-next-block}
                    {:program c/ze,    :action "move down",          :exec ze-move-down}]}         [c/ktcp_ad     [c/kt_p]       c/term]

    ^{:doc/actions [{}]}                                                                           [c/ktcsp_al    [c/ktcs_al]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_ar    [c/ktcs_ar]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_au    [c/ktcs_au]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_ad    [c/ktcs_ad]]

    ; technical glyphs
    ^{:doc/actions [{}]}                                                                           [c/ktcp_ob     [c/ktc_ob]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_cb     [c/ktc_cb]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_sc     [c/ktc_sc]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_qu     [c/ktc_qu]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_bl     [c/ktc_bl]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_cm     [c/ktc_cm]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_pe     [c/ktc_pe]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_sl     [c/ktc_sl]]

    ^{:doc/actions [{}]}                                                                           [c/ktcsp_ob    [c/ktcs_ob]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_cb    [c/ktcs_cb]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_sc    [c/ktcs_sc]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_qu    [c/ktcs_qu]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_bl    [c/ktcs_bl]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_cm    [c/ktcs_cm]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_pe    [c/ktcs_pe]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_sl    [c/ktcs_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/hc,    :action "close tab",          :exec hc-close-tab}
                    {:program c/mc,    :action "close tab",          :exec mc-close-tab}]}         [c/ktcp_db     [c/kt_l]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "close others",       :exec hc-unsplit}
                    {:program c/mc,    :action "close others",       :exec mc-unsplit}]}           [c/ktcp_re     [c/kt_g]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "split right",        :exec hc-vsplit}
                    {:program c/mc,    :action "split right",        :exec mc-vsplit}]}            [c/ktcp_rs     [c/kt_v]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "split down",         :exec hc-hsplit}
                    {:program c/mc,    :action "split down",         :exec mc-hsplit}]}            [c/ktcp_ro     [c/kt_h]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "close window",       :exec hc-close-split}
                    {:program c/mc,    :action "close window",       :exec mc-close-split}]}       [c/ktcp_rc     [c/kt_j]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "last file",          :exec hc-last-tab}
                    {:program c/br,    :action "open preview"        :exec br-toggle-preview}]}    [c/ktcp_sp     [c/kt_o]       c/term]

    ^{:doc/actions [{}]}                                                                           [c/ktcsp_db    [c/ktcs_db]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_re    [c/ktcs_re]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_rs    [c/ktcs_rs]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_ro    [c/ktcs_ro]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_rc    [c/ktcs_rc]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_sp    [c/ktcs_sp]]

    ; numeric-glyphs
    ^{:doc/actions [{}]}                                                                           [c/ktcp_1      [c/ktc_1]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_2      [c/ktc_2]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_3      [c/ktc_3]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_4      [c/ktc_4]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_5      [c/ktc_5]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_6      [c/ktc_6]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_7      [c/ktc_7]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_8      [c/ktc_8]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_9      [c/ktc_9]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_0      [c/ktc_0]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_hy     [c/ktc_hy]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_eq     [c/ktc_eq]]

    ^{:doc/actions [{}]}                                                                           [c/ktcsp_1     [c/ktcs_1]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_2     [c/ktcs_2]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_3     [c/ktcs_3]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_4     [c/ktcs_4]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_5     [c/ktcs_5]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_6     [c/ktcs_6]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_7     [c/ktcs_7]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_8     [c/ktcs_8]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_9     [c/ktcs_9]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_0     [c/ktcs_0]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_hy    [c/ktcs_hy]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_eq    [c/ktcs_eq]]

    ; alphabetic-glyphs
    ^{:doc/actions [{}]}                                                                           [c/ktcp_a      [c/ktc_a]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_b      [c/ktc_b]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_c      [c/ktc_c]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_d      [c/ktc_d]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_e      [c/ktc_e]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_f      [c/ktc_f]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_g      [c/ktc_g]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_h      [c/ktc_h]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_i      [c/ktc_i]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_j      [c/ktc_j]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_k      [c/ktc_k]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_l      [c/ktc_l]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_m      [c/ktc_m]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_n      [c/ktc_n]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_o      [c/ktc_o]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_p      [c/ktc_p]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_q      [c/ktc_q]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_r      [c/ktc_r]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_s      [c/ktc_s]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_t      [c/ktc_t]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_u      [c/ktc_u]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_v      [c/ktc_v]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_w      [c/ktc_w]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_x      [c/ktc_x]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_y      [c/ktc_y]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_z      [c/ktc_z]]
    ^{:doc/actions [{}]}                                                                           [c/ktcp_rt     [c/ktc_rt]]

    ^{:doc/actions [{}]}                                                                           [c/ktcsp_a     [c/ktcs_a]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_b     [c/ktcs_b]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_c     [c/ktcs_c]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_d     [c/ktcs_d]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_e     [c/ktcs_e]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_f     [c/ktcs_f]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_g     [c/ktcs_g]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_h     [c/ktcs_h]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_i     [c/ktcs_i]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_j     [c/ktcs_j]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_k     [c/ktcs_k]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_l     [c/ktcs_l]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_m     [c/ktcs_m]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_n     [c/ktcs_n]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_o     [c/ktcs_o]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_p     [c/ktcs_p]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_q     [c/ktcs_q]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_r     [c/ktcs_r]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_s     [c/ktcs_s]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_t     [c/ktcs_t]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_u     [c/ktcs_u]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_v     [c/ktcs_v]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_w     [c/ktcs_w]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_x     [c/ktcs_x]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_y     [c/ktcs_y]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_z     [c/ktcs_z]]
    ^{:doc/actions [{}]}                                                                           [c/ktcsp_rt    [c/ktcs_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lctlcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
