;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT-CMD
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.loptcmd
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
    ^{:doc/actions [{:program c/zj,    :action "prev tab",           :exec zj-prev-tab}]}          [r/kocp_al     [f/ko_f1]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "next tab",           :exec zj-next-tab}]}          [r/kocp_ar     [f/ko_f2]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "scroll up",          :exec hc-scroll-up}
                    {:program c/lg,    :action "scroll up",          :exec lg-scroll-up}]}         [r/kocp_au     [b/kt_x]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "scroll down",        :exec hc-scroll-down}
                    {:program c/lg,    :action "scroll down",        :exec lg-scroll-down}]}       [r/kocp_ad     [b/kt_y]       c/term]

    ^{:doc/actions [{}]}                                                                           [r/kocsp_al    [r/kocs_al]]
    ^{:doc/actions [{}]}                                                                           [r/kocsp_ar    [r/kocs_ar]]
    ^{:doc/actions [{}]}                                                                           [r/kocsp_au    [r/kocs_au]]
    ^{:doc/actions [{}]}                                                                           [r/kocsp_ad    [r/kocs_ad]]

    ; technical glyphs
    ^{:doc/actions [{:program c/zj,    :action "tab sync",           :exec zj-toggle-sync}]}       [t/kocp_ob     [f/ko_f4]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "plugin jump-list",   :exec zj-plug-jump}]}         [t/kocp_cb     [f/ko_f5]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "plugin monocle",     :exec zj-plug-monocle}]}      [t/kocp_sc     [f/ko_f6]      c/term]
    ^{:doc/actions [{}]}                                                                           [t/kocp_qu     [b/kos_y]      c/term]
    ^{:doc/actions [{}]}                                                                           [t/kocp_bl     [b/kos_z]      c/term]
    ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [t/kocp_pe     [b/ko_x]       c/term]
    ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [t/kocp_cm     [b/ko_y]       c/term]
    ^{:doc/actions [{:program c/ay,    :action "nushell motion",     :exec nu}]}                   [t/kocp_sl     [b/ko_z]       c/term]

    ^{:doc/actions [{}]}                                                                           [t/kocsp_ob    [t/kocs_ob]]
    ^{:doc/actions [{}]}                                                                           [t/kocsp_cb    [t/kocs_cb]]
    ^{:doc/actions [{}]}                                                                           [t/kocsp_sc    [t/kocs_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kocsp_qu    [t/kocs_qu]]
    ^{:doc/actions [{}]}                                                                           [t/kocsp_bl    [t/kocs_bl]]
    ^{:doc/actions [{}]}                                                                           [t/kocsp_cm    [t/kocs_cm]]
    ^{:doc/actions [{}]}                                                                           [t/kocsp_pe    [t/kocs_pe]]
    ^{:doc/actions [{}]}                                                                           [t/kocsp_sl    [t/kocs_sl]]

    ; action glyphs
    ^{:doc/actions [{:program c/zj,    :action "tab close",          :exec zj-close-tab}]}         [a/kocp_db     [f/ko_f7]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "plugin room",        :exec zj-plug-room}]}         [a/kocp_re     [f/ko_f8]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "tab new",            :exec zj-new-tab}]}           [a/kocp_rs     [f/ko_f9]      c/term]
    ^{:doc/actions [{:program c/zj,    :action "tab break pane",     :exec zj-break-pane}]}        [a/kocp_ro     [f/ko_f10]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "mode tab rename",    :exec zj-rename-tab-mode}
                    {:program c/zt,    :action "abort tab reanme",   :exec zt-abort-rename}]}      [a/kocp_rc     [f/ko_f11]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "tab jump back",      :exec zj-last-tab}]}          [a/kocp_sp     [f/ko_f12]     c/term]

    ^{:doc/actions [{}]}                                                                           [a/kocsp_db    [a/kocs_db]]
    ^{:doc/actions [{}]}                                                                           [a/kocsp_re    [a/kocs_re]]
    ^{:doc/actions [{}]}                                                                           [a/kocsp_rs    [a/kocs_rs]]
    ^{:doc/actions [{}]}                                                                           [a/kocsp_ro    [a/kocs_ro]]
    ^{:doc/actions [{}]}                                                                           [a/kocsp_rc    [a/kocs_rc]]
    ^{:doc/actions [{}]}                                                                           [a/kocsp_sp    [a/kocs_sp]]

    ; numeric glyphs
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-1",          :exec zj-tab-1}]}             [n/kocp_1      [f/kos_f1]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-2",          :exec zj-tab-2}]}             [n/kocp_2      [f/kos_f2]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-3",          :exec zj-tab-3}]}             [n/kocp_3      [f/kos_f4]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-4",          :exec zj-tab-4}]}             [n/kocp_4      [f/kos_f5]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-5",          :exec zj-tab-5}]}             [n/kocp_5      [f/kos_f6]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-6",          :exec zj-tab-6}]}             [n/kocp_6      [f/kos_f7]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-7",          :exec zj-tab-7}]}             [n/kocp_7      [f/kos_f8]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-8",          :exec zj-tab-8}]}             [n/kocp_8      [f/kos_f9]     c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-9",          :exec zj-tab-9}]}             [n/kocp_9      [f/kos_f10]    c/term]
    ^{:doc/actions [{:program c/zj,    :action "alt-cmd-0",          :exec zj-tab-9}]}             [n/kocp_0      [f/kos_f11]    c/term]
    ^{:doc/actions [{}]}                                                                           [n/kocp_hy     [n/kotc_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kocp_eq     [n/kotc_eq]]

    ^{:doc/actions [{}]}                                                                           [n/kocsp_1     [n/kocs_1]]
    ^{:doc/actions [{}]}                                                                           [n/kocsp_2     [n/kocs_2]]
    ^{:doc/actions [{}]}                                                                           [n/kocsp_3     [n/kocs_3]]
    ^{:doc/actions [{}]}                                                                           [n/kocsp_4     [n/kocs_4]]
    ^{:doc/actions [{}]}                                                                           [n/kocsp_5     [n/kocs_5]]
    ^{:doc/actions [{}]}                                                                           [n/kocsp_6     [n/kocs_6]]
    ^{:doc/actions [{}]}                                                                           [n/kocsp_7     [n/kocs_7]]
    ^{:doc/actions [{}]}                                                                           [n/kocsp_8     [n/kocs_8]]
    ^{:doc/actions [{}]}                                                                           [n/kocsp_9     [n/kocs_9]]
    ^{:doc/actions [{}]}                                                                           [n/kocsp_0     [n/kocs_0]]
    ^{:doc/actions [{}]}                                                                           [n/kocsp_hy    [n/kocs_hy]]
    ^{:doc/actions [{}]}                                                                           [n/kocsp_eq    [n/kocs_eq]]

    ; alphabetic glyphs
    ^{:doc/actions [{}]}                                                                           [b/kocp_a      [b/koc_a]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_b      [b/koc_b]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_c      [b/koc_c]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_d      [b/koc_d]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_e      [b/koc_e]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_f      [b/koc_f]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_g      [b/koc_g]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_h      [b/koc_h]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_i      [b/koc_i]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_j      [b/koc_j]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_k      [b/koc_k]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_l      [b/koc_l]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_m      [b/koc_m]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_n      [b/koc_n]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_o      [b/koc_o]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_p      [b/koc_p]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_q      [b/koc_q]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_r      [b/koc_r]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_s      [b/koc_s]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_t      [b/koc_t]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_u      [b/koc_u]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_v      [b/koc_v]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_w      [b/koc_w]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_x      [b/koc_x]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_y      [b/koc_y]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_z      [b/koc_z]]
    ^{:doc/actions [{}]}                                                                           [b/kocp_rt     [b/koc_rt]]

    ^{:doc/actions [{}]}                                                                           [b/kocsp_a     [b/kocs_a]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_b     [b/kocs_b]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_c     [b/kocs_c]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_d     [b/kocs_d]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_e     [b/kocs_e]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_f     [b/kocs_f]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_g     [b/kocs_g]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_h     [b/kocs_h]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_i     [b/kocs_i]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_j     [b/kocs_j]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_k     [b/kocs_k]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_l     [b/kocs_l]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_m     [b/kocs_m]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_n     [b/kocs_n]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_o     [b/kocs_o]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_p     [b/kocs_p]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_q     [b/kocs_q]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_r     [b/kocs_r]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_s     [b/kocs_s]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_t     [b/kocs_t]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_u     [b/kocs_u]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_v     [b/kocs_v]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_w     [b/kocs_w]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_x     [b/kocs_x]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_y     [b/kocs_y]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_z     [b/kocs_z]]
    ^{:doc/actions [{}]}                                                                           [b/kocsp_rt    [b/kocs_rt]]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (loptcmd)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
