;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT-CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.loptcmd
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "loptcmd.edn")

(def zj-plug-jump            ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/zellij-jump-list.wasm\" { floating true; move_to_focused_tab true; };"])
(def zj-plug-monocle         ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/monocle.wasm\" { floating true; }; SwitchToMode \"Normal\";"])
(def zj-plug-room            ["LaunchOrFocusPlugin \"file:~/.config/zellij/plugins/room.wasm\" { floating true; ignore_case true; };"])

(def zj-prev-tab             ["GoToPreviousTab;"])
(def zj-next-tab             ["GoToNextTab;"])
(def hc-scroll-up            ["scroll_up"])
(def lg-scroll-up            ["scrollUpMain-alt2"])
(def hc-scroll-down          ["scroll_down"])
(def lg-scroll-down          ["scrollDownMain-alt2"])
(def zj-toggle-sync          ["ToggleActiveSyncTab;"])
(def nu                      [])
(def zj-close-tab            ["CloseTab;"])
(def zj-new-tab              ["NewTab;"])
(def zj-break-pane           ["BreakPane;"])
(def zj-rename-tab-mode      ["SwitchToMode \"RenameTab\"; TabNameInput 0;"])
(def zt-abort-rename         ["UndoRenameTab; SwitchToMode \"Normal\";"])
(def zj-last-tab             ["ToggleTab;"])
(def zj-tab-1                ["SwitchToMode \"Tab\"; GoToTab 1; SwitchToMode \"Normal\";"])
(def zj-tab-2                ["SwitchToMode \"Tab\"; GoToTab 2; SwitchToMode \"Normal\";"])
(def zj-tab-3                ["SwitchToMode \"Tab\"; GoToTab 3; SwitchToMode \"Normal\";"])
(def zj-tab-4                ["SwitchToMode \"Tab\"; GoToTab 4; SwitchToMode \"Normal\";"])
(def zj-tab-5                ["SwitchToMode \"Tab\"; GoToTab 5; SwitchToMode \"Normal\";"])
(def zj-tab-6                ["SwitchToMode \"Tab\"; GoToTab 6; SwitchToMode \"Normal\";"])
(def zj-tab-7                ["SwitchToMode \"Tab\"; GoToTab 7; SwitchToMode \"Normal\";"])
(def zj-tab-8                ["SwitchToMode \"Tab\"; GoToTab 8; SwitchToMode \"Normal\";"])
(def zj-tab-9                ["SwitchToMode \"Tab\"; GoToTab 9; SwitchToMode \"Normal\";"])

(defn loptcmd []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option - Command Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/zj,    :action "jump prev buffer",   :exec zj-prev-tab}]}          [c/kocp_al     [c/kos_j]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "jump next buffer",   :exec zj-next-tab}]}          [c/kocp_ar     [c/kos_k]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "scroll up",          :exec hc-scroll-up}
                    {:program c/lg,    :action "scroll up",          :exec lg-scroll-up}]}         [c/kocp_au     [c/kt_x]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "scroll down",        :exec hc-scroll-down}
                    {:program c/lg,    :action "scroll down",        :exec lg-scroll-down}]}       [c/kocp_ad     [c/kt_y]       c/term]

    ^{:doc/actions [{}]}                                                                           [c/kocsp_al    [c/kocs_al]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_ar    [c/kocs_ar]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_au    [c/kocs_au]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_ad    [c/kocs_ad]]

    ; technical glyphs
    ^{:doc/actions [{:program c/zj,    :action "tab sync",           :exec zj-toggle-sync}]}       [c/kocp_ob     [c/kos_v]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "plugin jump-list",   :exec zj-plug-jump}]}         [c/kocp_cb     [c/kos_w]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "plugin monocle",     :exec zj-plug-monocle}]}      [c/kocp_sc     [c/kos_x]      c/term]
    ^{:doc/actions [{}]}                                                                           [c/kocp_qu     [c/kos_y]      c/term]
    ^{:doc/actions [{}]}                                                                           [c/kocp_bl     [c/kos_z]      c/term]
    ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [c/kocp_pe     [c/ko_x]       c/term]
    ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [c/kocp_cm     [c/ko_y]       c/term]
    ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [c/kocp_sl     [c/ko_z]       c/term]

    ^{:doc/actions [{}]}                                                                           [c/kocsp_ob    [c/kocs_ob]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_cb    [c/kocs_cb]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_sc    [c/kocs_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_qu    [c/kocs_qu]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_bl    [c/kocs_bl]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_cm    [c/kocs_cm]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_pe    [c/kocs_pe]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_sl    [c/kocs_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/zj,    :action "tab close",          :exec zj-close-tab}]}         [c/kocp_db     [c/kos_u]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "plugin room",        :exec zj-plug-room}]}         [c/kocp_re     [c/kos_p]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "tab new",            :exec zj-new-tab}]}           [c/kocp_rs     [c/kos_q]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "tab break pane",     :exec zj-break-pane}]}        [c/kocp_ro     [c/kos_r]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "mode tab rename",    :exec zj-rename-tab-mode}
                    {:program c/zt,    :action "abort tab reanme",   :exec zt-abort-rename}]}      [c/kocp_rc     [c/kos_s]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "tab jump back",      :exec zj-last-tab}]}          [c/kocp_sp     [c/kos_t]      c/term]

    ^{:doc/actions [{}]}                                                                           [c/kocsp_db    [c/kocs_db]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_re    [c/kocs_re]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_rs    [c/kocs_rs]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_ro    [c/kocs_ro]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_rc    [c/kocs_rc]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_sp    [c/kocs_sp]]

    ; numeric glyphs
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-1",          :exec zj-tab-1}]}             [c/kocp_1      [c/kos_a]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-2",          :exec zj-tab-2}]}             [c/kocp_2      [c/kos_b]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-3",          :exec zj-tab-3}]}             [c/kocp_3      [c/kos_c]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-4",          :exec zj-tab-4}]}             [c/kocp_4      [c/kos_d]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-5",          :exec zj-tab-5}]}             [c/kocp_5      [c/kos_e]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-6",          :exec zj-tab-6}]}             [c/kocp_6      [c/kos_f]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-7",          :exec zj-tab-7}]}             [c/kocp_7      [c/kos_g]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-8",          :exec zj-tab-8}]}             [c/kocp_8      [c/kos_h]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-9",          :exec zj-tab-9}]}             [c/kocp_9      [c/kos_i]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-0",          :exec zj-tab-9}]}             [c/kocp_0      [c/kos_j]      c/term]
    ^{:doc/actions [{}]}                                                                           [c/kocp_hy     [c/kotc_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_eq     [c/kotc_eq]]

    ^{:doc/actions [{}]}                                                                           [c/kocsp_1     [c/kocs_1]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_2     [c/kocs_2]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_3     [c/kocs_3]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_4     [c/kocs_4]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_5     [c/kocs_5]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_6     [c/kocs_6]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_7     [c/kocs_7]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_8     [c/kocs_8]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_9     [c/kocs_9]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_0     [c/kocs_0]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_hy    [c/kocs_hy]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_eq    [c/kocs_eq]]

    ; alphabetic glyphs
    ^{:doc/actions [{}]}                                                                           [c/kocp_a      [c/koc_a]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_b      [c/koc_b]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_c      [c/koc_c]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_d      [c/koc_d]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_e      [c/koc_e]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_f      [c/koc_f]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_g      [c/koc_g]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_h      [c/koc_h]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_i      [c/koc_i]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_j      [c/koc_j]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_k      [c/koc_k]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_l      [c/koc_l]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_m      [c/koc_m]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_n      [c/koc_n]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_o      [c/koc_o]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_p      [c/koc_p]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_q      [c/koc_q]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_r      [c/koc_r]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_s      [c/koc_s]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_t      [c/koc_t]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_u      [c/koc_u]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_v      [c/koc_v]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_w      [c/koc_w]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_x      [c/koc_x]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_y      [c/koc_y]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_z      [c/koc_z]]
    ^{:doc/actions [{}]}                                                                           [c/kocp_rt     [c/koc_rt]]

    ^{:doc/actions [{}]}                                                                           [c/kocsp_a     [c/kocs_a]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_b     [c/kocs_b]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_c     [c/kocs_c]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_d     [c/kocs_d]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_e     [c/kocs_e]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_f     [c/kocs_f]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_g     [c/kocs_g]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_h     [c/kocs_h]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_i     [c/kocs_i]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_j     [c/kocs_j]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_k     [c/kocs_k]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_l     [c/kocs_l]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_m     [c/kocs_m]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_n     [c/kocs_n]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_o     [c/kocs_o]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_p     [c/kocs_p]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_q     [c/kocs_q]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_r     [c/kocs_r]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_s     [c/kocs_s]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_t     [c/kocs_t]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_u     [c/kocs_u]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_v     [c/kocs_v]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_w     [c/kocs_w]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_x     [c/kocs_x]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_y     [c/kocs_y]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_z     [c/kocs_z]]
    ^{:doc/actions [{}]}                                                                           [c/kocsp_rt    [c/kocs_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (loptcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
