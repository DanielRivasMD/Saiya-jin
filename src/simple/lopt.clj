;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lopt
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config.config :as c]
            [config.arrows :as r]
            [config.technical :as t]
            [config.action :as a]
            [config.numeric :as n]
            [config.alphabetic :as b]
						))

(def out-file "lopt.edn")

(def hc-prev-word            ["move_prev_word_start", "collapse_selection"])
(def hc-next-word            ["move_next_word_end", "collapse_selection"])
(def hc-prev-para            ["goto_prev_paragraph"  "collapse_selection"])
(def lg-prev-page            ["prevPage"])
(def mc-prev-para            ["ParagraphPrevious"])
(def ze-half-up              ["HalfPageScrollUp"])
(def hc-next-para            ["goto_next_paragraph"  "collapse_selection"])
(def lg-next-page            ["nextPage"])
(def mc-next-para            ["ParagraphNext"])
(def ze-half-down            ["HalfPageScrollDown"])
(def hc-select-prev-word     ["extend_prev_word_start"])
(def mc-select-prev-word     ["SelectWordLeft"])
(def hc-select-next-word     ["extend_next_word_end"])
(def mc-select-next-word     ["SelectWordRight"])
(def hi-select-prev-para     ["select_mode" "goto_prev_paragraph" "insert_mode"])
(def hn-select-prev-para     ["select_mode" "goto_prev_paragraph" "normal_mode"])
(def hs-select-prev-para     ["select_mode" "goto_prev_paragraph" "select_mode"])
(def mc-select-prev-para     ["SelectToParagraphPrevious"])
(def hi-select-next-para     ["select_mode" "goto_next_paragraph" "append_mode"])
(def hn-select-next-para     ["select_mode" "goto_next_paragraph" "normal_mode"])
(def hs-select-next-para     ["select_mode" "goto_next_paragraph" "select_mode"])
(def mc-select-next-para     ["SelectToParagraphNext"])

(def hc-format               [":format"])
(def hc-copy                 ["yank_to_clipboard"])
(def mc-copy                 ["Copy"])
(def hc-spawn-multi          ["search_selection", "extend_search_next"])
(def mc-spawn-multi          ["SpawnMultiCursor"])
(def hc-toggle-com           ["toggle_comments"])
(def mc-toggle-com           ["lua:comment.comment"])
(def hc-search               ["search"])
(def mc-search               ["FindLiteral"])
(def hc-find-prev            ["search_prev"])
(def mc-find-prev            ["FindPrevious"])
(def hc-spawn-down           ["normal_mode", "copy_selection_on_next_line", "MODE"])
(def mc-spawn-down           ["SpawnMultiCursorDown"])
(def hc-spawn-up             ["normal_mode", "copy_selection_on_prev_line", "MODE"])
(def mc-spawn-up             ["SpawnMultiCursorUp"])
(def hc-find-next            ["search_next"])
(def mc-find-next            ["FindNext"])
(def hc-record               ["record_macro"])
(def mc-record               ["ToggleMacro"])
(def hc-new                  [":new"])
(def mc-new                  ["AddTab"])
(def hc-quit                 ["normal_mode", ":quit-all"])
(def mc-quit                 ["QuitAll"])
(def hc-reload               ["normal_mode", ":reload-all", "MODE"])
(def hc-write-quit           ["normal_mode", ":write-quit-all"])
(def mc-write-quit           ["Save,QuitAll"])
(def hc-paste                ["paste_clipboard_before"])
(def mc-paste                ["Paste"])
(def hc-write                ["normal_mode", ":write"])
(def mc-write                ["Save"])
(def hc-todor-compile        [":sh just todor"])
(def hc-cut                  ["yank_to_clipboard", "delete_selection_noyank"])
(def mc-cut                  ["Cut"])
(def hc-skip-multi           ["search_selection", "search_next"])
(def mc-skip-multi           ["SkipMultiCursor"])
(def hc-rm-multi             ["search_selection", "search_prev"])
(def mc-rm-multi             ["RemoveMultiCursor"])
(def hc-cmd                  ["command_mode"])
(def mc-cmd                  ["CommandMode"])

(def hc-select-all           ["select_all"])
(def mc-select-all           ["SelectAll"])
(def hc-copy-line            ["extend_to_line_bounds", "yank_to_clipboard"])
(def mc-copy-line            ["CopyLine"])
(def hc-dup-line             ["extend_to_line_bounds", "yank", "paste_after", "goto_line_start"])
(def mc-dup-line             ["DuplicateLine"])
(def hc-global-search        ["global_search"])
(def mc-global-search        ["Find"])
(def hc-indent               ["indent"])
(def mc-indent               ["IndentSelection"])
(def hc-line-down            ["normal_mode", "extend_to_line_bounds", "delete_selection", "paste_after", "MODE"])
(def mc-line-down            ["MoveLinesDown"])
(def hc-line-up              ["normal_mode", "extend_to_line_bounds", "delete_selection", "move_line_up", "paste_before", "MODE"])
(def mc-line-up              ["MoveLinesUp"])
(def hc-select-line          ["goto_line_end", "select_mode", "goto_line_start", "MODE"])
(def mc-select-line          ["SelectLine"])
(def hc-play                 ["replay_macro"])
(def mc-play                 ["PlayMacro"])
(def hc-unindent             ["unindent"])
(def mc-unindent             ["OutdentSelection"])
(def hc-lazygit              [":sh zellij run --name lazygit --pinned true  --close-on-exit --floating  --width 180 --height  60 --x   0 --y   0 -- lazygit"])
(def hc-serpl                [":sh zellij run --name serpl   --pinned true  --close-on-exit --floating  --width 180 --height  60 --x   0 --y   0 -- serpl"])
(def hc-todor-interactive    [":sh zellij run --name todor   --pinned true  --close-on-exit --floating  --width  90 --height  30 --x  90 --y   0 -- todor --delete"])
(def hc-watch                [":sh zellij run --name canvas  --pinned true  --close-on-exit --floating  --width  45 --height  20 --x 135 --y   0 -- just watch"])
(def hc-cut-line             ["extend_to_line_bounds", "delete_selection_noyank"])
(def mc-cut-line             ["CutLine"])
(def hc-copy-diag            [":yank-diagnostic"])
(def hc-shell                [":sh zellij run --name canvas  --pinned false --close-on-exit --floating  --width 180 --height  90 --x   0 --y   0 -- zsh"])
(def mc-shell                ["ShellMode"])

(defn lopt []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "goto prev word",     :exec hc-prev-word}]}         [r/kop_al      [b/ko_b]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto next word",     :exec hc-next-word}]}         [r/kop_ar      [b/ko_f]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto prev para",     :exec hc-prev-para}
                    {:program c/lg,    :action "goto prev page",     :exec lg-prev-page}
                    {:program c/mc,    :action "goto prev para",     :exec mc-prev-para}
                    {:program c/ze,    :action "half page up",       :exec ze-half-up}]}           [r/kop_au      [r/k_pu]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto next para",     :exec hc-next-para}
                    {:program c/lg,    :action "goto next page",     :exec lg-next-page}
                    {:program c/mc,    :action "goto next para",     :exec mc-next-para}
                    {:program c/ze,    :action "half page down",     :exec ze-half-down}]}         [r/kop_ad      [r/k_pd]       c/term]

    ^{:doc/actions [{:program c/hc,    :action "select prev word",   :exec hc-select-prev-word}
                    {:program c/mc,    :action "select prev word",   :exec mc-select-prev-word}]}  [r/kosp_al     [r/kos_al]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "select next word",   :exec hc-select-next-word}
                    {:program c/mc,    :action "select next word",   :exec mc-select-next-word}]}  [r/kosp_ar     [r/kos_ar]     c/term]
    ^{:doc/actions [{:program c/hi,    :action "select prev para",   :exec hi-select-prev-para}
                    {:program c/hn,    :action "select prev para",   :exec hn-select-prev-para}
                    {:program c/hs,    :action "select prev para",   :exec hs-select-prev-para}
                    {:program c/mc,    :action "select prev para",   :exec mc-select-prev-para}]}  [r/kosp_au     [r/kos_au]     c/term]
    ^{:doc/actions [{:program c/hi,    :action "select next para",   :exec hi-select-next-para}
                    {:program c/hn,    :action "select next para",   :exec hn-select-next-para}
                    {:program c/hs,    :action "select next para",   :exec hs-select-next-para}
                    {:program c/mc,    :action "select next para",   :exec mc-select-next-para}]}  [r/kosp_ad     [r/kos_ad]     c/term]

    ; technical glyphs
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` <= `"}]}         [t/kop_ob      [a/k_sp t/ks_cm n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` >= `"}]}         [t/kop_cb      [a/k_sp t/ks_pe n/k_eq a/k_sp]]
    ^{:doc/actions [{}]}                                                                           [t/kop_sc      [t/ko_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kop_qu      [t/ko_qu]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` | `"}]}          [t/kop_bl      [a/k_sp t/ks_bl a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` < `"}]}          [t/kop_cm      [a/k_sp t/ks_cm a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` > `"}]}          [t/kop_pe      [a/k_sp t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ? `"}]}          [t/kop_sl      [a/k_sp t/ks_sl a/k_sp]]

    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .<= `"}]}        [t/kosp_ob     [a/k_sp t/k_pe t/ks_cm n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .>= `"}]}        [t/kosp_cb     [a/k_sp t/k_pe t/ks_pe n/k_eq a/k_sp]]
    ^{:doc/actions [{}]}                                                                           [t/kosp_sc     [t/kos_sc]]
    ^{:doc/actions [{}]}                                                                           [t/kosp_qu     [t/kos_qu]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .| `"}]}         [t/kosp_bl     [a/k_sp t/k_pe t/ks_bl a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .< `"}]}         [t/kosp_cm     [a/k_sp t/k_pe t/ks_cm a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .> `"}]}         [t/kosp_pe     [a/k_sp t/k_pe t/ks_pe a/k_sp]]
    ^{:doc/actions [{}]}                                                                           [t/kosp_sl     [t/kos_sl]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [a/kop_db      [a/ko_db]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` = `"}]}          [a/kop_re      [a/k_sp n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` |> `"}]}         [a/kop_rs      [a/k_sp t/ks_bl t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` * `"}]}          [a/kop_ro      [a/k_sp n/ks_8 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` / `"}]}          [a/kop_rc      [a/k_sp t/k_sl a/k_sp]]
    ^{:doc/actions [{:program c/alf,   :action "prompt"}]}                                         [a/kop_sp      [c/koc_us]]

    ^{:doc/actions [{}]}                                                                           [a/kosp_db     [a/kosp_db]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .= `"}]}         [a/kosp_re     [a/k_sp t/k_pe n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .|> `"}]}        [a/kosp_rs     [a/k_sp t/k_pe t/ks_bl t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .* `"}]}         [a/kosp_ro     [a/k_sp t/k_pe n/ks_8 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ./ `"}]}         [a/kosp_rc     [a/k_sp t/k_pe t/k_sl a/k_sp]]
    ^{:doc/actions [{:program c/alf,   :action "file prompt"}]}                                    [a/kosp_sp     [c/kocs_us]]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [n/kop_1       [n/ko_1]]
    ^{:doc/actions [{}]}                                                                           [n/kop_2       [n/ko_2]]
    ^{:doc/actions [{}]}                                                                           [n/kop_3       [n/ko_3]]
    ^{:doc/actions [{}]}                                                                           [n/kop_4       [n/ko_4]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` % `"}]}          [n/kop_5       [a/k_sp n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ^ `"}]}          [n/kop_6       [a/k_sp n/ks_6 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` & `"}]}          [n/kop_7       [a/k_sp n/ks_7 a/k_sp]]
    ^{:doc/actions [{}]}                                                                           [n/kop_8       [n/ko_8]]
    ^{:doc/actions [{}]}                                                                           [n/kop_9       [n/ko_9]]
    ^{:doc/actions [{}]}                                                                           [n/kop_0       [n/ko_0]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` - `"}]}          [n/kop_hy      [a/k_sp n/k_hy a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` + `"}]}          [n/kop_eq      [a/k_sp n/ks_eq a/k_sp]]

    ^{:doc/actions [{}]}                                                                           [n/kosp_1      [n/kos_1]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_2      [n/kos_2]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_3      [n/kos_3]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_4      [n/kos_4]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .% `"}]}         [n/kosp_5      [a/k_sp t/k_pe n/ks_5 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .^ `"}]}         [n/kosp_6      [a/k_sp t/k_pe n/ks_6 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .& `"}]}         [n/kosp_7      [a/k_sp t/k_pe n/ks_7 a/k_sp]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_8      [n/kos_8]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_9      [n/kos_9]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_0      [n/kos_0]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .- `"}]}         [n/kosp_hy     [a/k_sp t/k_pe n/k_hy a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .+ `"}]}         [n/kosp_eq     [a/k_sp t/k_pe n/ks_eq a/k_sp]]

    ; TODO: launch float pane with editor (hx / mc); independent, or with reference
    ; alphabetic glyphs
    ^{:doc/actions [{:program c/hc,    :action "format",             :exec hc-format}]}            [b/kop_a       [:!Of1]        c/term]
    ^{:doc/actions [{}]}                                                                           [b/kop_b       [:!Of2]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "copy",               :exec hc-copy}
                    {:program c/mc,    :action "copy",               :exec mc-copy}]}              [b/kop_c       [:!Of4]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi",        :exec hc-spawn-multi}
                    {:program c/mc,    :action "spawn multi",        :exec mc-spawn-multi}]}       [b/kop_d       [:!Of5]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "toggle comments",    :exec hc-toggle-com}
                    {:program c/mc,    :action "toggle comments",    :exec mc-toggle-com}]}        [b/kop_e       [:!Of6]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "search",             :exec hc-search}
                    {:program c/mc,    :action "search",             :exec mc-search}]}            [b/kop_f       [:!Of7]        c/term]
    ^{:doc/actions [{}]}                                                                           [b/kop_g       [:!Of8]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "find prev",          :exec hc-find-prev}
                    {:program c/mc,    :action "find prev",          :exec mc-find-prev}]}         [b/kop_h       [:!Of9]        c/term]
    ^{:doc/actions [{}]}                                                                           [b/kop_i       [:!Of10]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi down",   :exec hc-spawn-down}
                    {:program c/mc,    :action "spawn multi down",   :exec mc-spawn-down}]}        [b/kop_j       [:!Tf1]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi up",     :exec hc-spawn-up}
                    {:program c/mc,    :action "spawn multi up",     :exec mc-spawn-up}]}          [b/kop_k       [:!Tf2]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "find next",          :exec hc-find-next}
                    {:program c/mc,    :action "find next",          :exec mc-find-next}]}         [b/kop_l       [:!Tf4]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "toggle macro",       :exec hc-record}
                    {:program c/mc,    :action "toggle macro",       :exec mc-record}]}            [b/kop_m       [:!Tf5]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "new buffer",         :exec hc-new}
                    {:program c/mc,    :action "new buffer",         :exec mc-new}]}               [b/kop_n       [:!Tf6]        c/term]
    ^{:doc/actions [{}]}                                                                           [b/kop_o       [:!Tf7]        c/term]
    ^{:doc/actions [{}]}                                                                           [b/kop_p       [:!Tf8]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "quit",               :exec hc-quit}
                    {:program c/mc,    :action "quit",               :exec mc-quit}]}              [b/kop_q       [:!Tf9]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "reload buffers",     :exec hc-reload}]}            [b/kop_r       [:!Tf10]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "save & quit",        :exec hc-write-quit}
                    {:program c/mc,    :action "save & quit",        :exec mc-write-quit}]}        [b/kop_s       [:!OTf1]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "compile todo",       :exec hc-todor-compile}]}     [b/kop_t       [:!OTf2]       c/term]
    ^{:doc/actions [{}]}                                                                           [b/kop_u       [:!OTf4]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "paste",              :exec hc-paste}
                    {:program c/mc,    :action "paste",              :exec mc-paste}]}             [b/kop_v       [:!OTf5]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "save",               :exec hc-write}
                    {:program c/mc,    :action "save",               :exec mc-write}]}             [b/kop_w       [:!OTf6]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "cut",                :exec hc-cut}
                    {:program c/mc,    :action "cut",                :exec mc-cut}]}               [b/kop_x       [:!OTf7]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "skip multi",         :exec hc-skip-multi}
                    {:program c/mc,    :action "skip multi",         :exec mc-skip-multi}]}        [b/kop_y       [:!OTf8]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "remove multi",       :exec hc-rm-multi}
                    {:program c/mc,    :action "remove multi",       :exec mc-rm-multi}]}          [b/kop_z       [:!OTf9]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "command mode",       :exec hc-cmd}
                    {:program c/mc,    :action "command mode",       :exec mc-cmd}]}               [b/kop_rt      [:!OTf10]      c/term]

    ^{:doc/actions [{:program c/hc,    :action "select all",         :exec hc-select-all}
                    {:program c/mc,    :action "select all",         :exec mc-select-all}]}        [b/kosp_a      [:!OSf1]       c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_b      [:!OSf2]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "copy line",          :exec hc-copy-line}
                    {:program c/mc,    :action "copy line",          :exec mc-copy-line}]}         [b/kosp_c      [:!OSf4]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "duplicate line",     :exec hc-dup-line}
                    {:program c/mc,    :action "duplicate line",     :exec mc-dup-line}]}          [b/kosp_d      [:!OSf5]       c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_e      [:!OSf6]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "global search",      :exec hc-global-search}
                    {:program c/mc,    :action "global search",      :exec mc-global-search}]}     [b/kosp_f      [:!OSf7]       c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_g      [:!OSf8]       c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_h      [:!OSf9]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "indent",             :exec hc-indent}
                    {:program c/mc,    :action "indent",             :exec mc-indent}]}            [b/kosp_i      [:!OSf10]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "move down",          :exec hc-line-down}
                    {:program c/mc,    :action "move down",          :exec mc-line-down}]}         [b/kosp_j      [:!TSf1]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "move up",            :exec hc-line-up}
                    {:program c/mc,    :action "move up",            :exec mc-line-up}]}           [b/kosp_k      [:!TSf2]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "select line",        :exec hc-select-line}
                    {:program c/mc,    :action "select line",        :exec mc-select-line}]}       [b/kosp_l      [:!TSf4]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "play macro",         :exec hc-play}
                    {:program c/mc,    :action "play macro",         :exec mc-play}]}              [b/kosp_m      [:!TSf5]       c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_n      [:!TSf6]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "unindent",           :exec hc-unindent}
                    {:program c/mc,    :action "unindent",           :exec mc-unindent}]}          [b/kosp_o      [:!TSf7]       c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_p      [:!TSf8]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch lazygit",     :exec hc-lazygit}]}           [b/kosp_q      [:!TSf9]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch serpl",       :exec hc-serpl}]}             [b/kosp_r      [:!TSf10]      c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_s      [:!OTSf1]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "interactive todo",   :exec hc-todor-interactive}]} [b/kosp_t      [:!OTSf2]      c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_u      [:!OTSf4]      c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_v      [:!OTSf5]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch watch",       :exec hc-watch}]}             [b/kosp_w      [:!OTSf6]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "cut line",           :exec hc-cut-line}
                    {:program c/mc,    :action "cut line",           :exec mc-cut-line}]}          [b/kosp_x      [:!OTSf7]      c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_y      [:!OTSf8]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "yank diagnostic",    :exec hc-copy-diag}]}         [b/kosp_z      [:!OTSf9]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch shell",       :exec hc-shell}
                    {:program c/mc,    :action "shell mode",         :exec mc-shell}]}             [b/kosp_rt     [:!OTSf10]     c/term]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lopt)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
