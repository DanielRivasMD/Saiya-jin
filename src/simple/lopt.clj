;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lopt
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

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
(def mc-select-prev-para     ["StartOfLine,SelectLine,ParagraphPrevious,SelectWordRight"])
(def hi-select-next-para     ["select_mode" "goto_next_paragraph" "append_mode"])
(def hn-select-next-para     ["select_mode" "goto_next_paragraph" "normal_mode"])
(def hs-select-next-para     ["select_mode" "goto_next_paragraph" "select_mode"])
(def mc-select-next-para     ["StartOfLine,SelectLine,ParagraphNext,SelectWordRight"])

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
    ^{:doc/actions [{:program c/hc,    :action "goto prev word",     :exec hc-prev-word}]}         [c/kop_al      [c/ko_b]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto next word",     :exec hc-next-word}]}         [c/kop_ar      [c/ko_f]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto prev para",     :exec hc-prev-para}
                    {:program c/lg,    :action "goto prev page",     :exec lg-prev-page}
                    {:program c/mc,    :action "goto prev para",     :exec mc-prev-para}
                    {:program c/ze,    :action "half page up",       :exec ze-half-up}]}           [c/kop_au      [c/k_pu]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto next para",     :exec hc-next-para}
                    {:program c/lg,    :action "goto next page",     :exec lg-next-page}
                    {:program c/mc,    :action "goto next para",     :exec mc-next-para}
                    {:program c/ze,    :action "half page down",     :exec ze-half-down}]}         [c/kop_ad      [c/k_pd]       c/term]

    ^{:doc/actions [{:program c/hc,    :action "select prev word",   :exec hc-select-prev-word}
                    {:program c/mc,    :action "select prev word",   :exec mc-select-prev-word}]}  [c/kosp_al     [c/kos_al]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "select next word",   :exec hc-select-next-word}
                    {:program c/mc,    :action "select next word",   :exec mc-select-next-word}]}  [c/kosp_ar     [c/kos_ar]     c/term]
    ^{:doc/actions [{:program c/hi,    :action "select prev para",   :exec hi-select-prev-para}
                    {:program c/hn,    :action "select prev para",   :exec hn-select-prev-para}
                    {:program c/hs,    :action "select prev para",   :exec hs-select-prev-para}
                    {:program c/mc,    :action "select prev para",   :exec mc-select-prev-para}]}  [c/kosp_au     [c/kos_au]     c/term]
    ^{:doc/actions [{:program c/hi,    :action "select next para",   :exec hi-select-next-para}
                    {:program c/hn,    :action "select next para",   :exec hn-select-next-para}
                    {:program c/hs,    :action "select next para",   :exec hs-select-next-para}
                    {:program c/mc,    :action "select next para",   :exec mc-select-next-para}]}  [c/kosp_ad     [c/kos_ad]     c/term]

    ; technical glyphs
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` <= `"}]}         [c/kop_ob      [c/k_sp c/ks_cm c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` >= `"}]}         [c/kop_cb      [c/k_sp c/ks_pe c/k_eq c/k_sp]]
    ^{:doc/actions [{}]}                                                                           [c/kop_sc      [c/ko_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kop_qu      [c/ko_qu]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` | `"}]}          [c/kop_bl      [c/k_sp c/ks_bl c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` < `"}]}          [c/kop_cm      [c/k_sp c/ks_cm c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` > `"}]}          [c/kop_pe      [c/k_sp c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ? `"}]}          [c/kop_sl      [c/k_sp c/ks_sl c/k_sp]]

    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .<= `"}]}        [c/kosp_ob     [c/k_sp c/k_pe c/ks_cm c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .>= `"}]}        [c/kosp_cb     [c/k_sp c/k_pe c/ks_pe c/k_eq c/k_sp]]
    ^{:doc/actions [{}]}                                                                           [c/kosp_sc     [c/kos_sc]]
    ^{:doc/actions [{}]}                                                                           [c/kosp_qu     [c/kos_qu]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .| `"}]}         [c/kosp_bl     [c/k_sp c/k_pe c/ks_bl c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .< `"}]}         [c/kosp_cm     [c/k_sp c/k_pe c/ks_cm c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .> `"}]}         [c/kosp_pe     [c/k_sp c/k_pe c/ks_pe c/k_sp]]
    ^{:doc/actions [{}]}                                                                           [c/kosp_sl     [c/kos_sl]]

    ; action glyphs
    ^{:doc/actions [{}]}                                                                           [c/kop_db      [c/ko_db]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` = `"}]}          [c/kop_re      [c/k_sp c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` |> `"}]}         [c/kop_rs      [c/k_sp c/ks_bl c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` * `"}]}          [c/kop_ro      [c/k_sp c/ks_8 c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` / `"}]}          [c/kop_rc      [c/k_sp c/k_sl c/k_sp]]
    ^{:doc/actions [{:program "Alfred",      :action "prompt"}]}                                   [c/kop_sp      [:!OCnon_us_pound]]

    ^{:doc/actions [{}]}                                                                           [c/kosp_db     [c/kosp_db]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .= `"}]}         [c/kosp_re     [c/k_sp c/k_pe c/k_eq c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .|> `"}]}        [c/kosp_rs     [c/k_sp c/k_pe c/ks_bl c/ks_pe c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .* `"}]}         [c/kosp_ro     [c/k_sp c/k_pe c/ks_8 c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ./ `"}]}         [c/kosp_rc     [c/k_sp c/k_pe c/k_sl c/k_sp]]
    ^{:doc/actions [{:program "Alfred",      :action "file prompt"}]}                              [c/kosp_sp     [:!OCSnon_us_pound]]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [c/kop_1       [c/ko_1]]
    ^{:doc/actions [{}]}                                                                           [c/kop_2       [c/ko_2]]
    ^{:doc/actions [{}]}                                                                           [c/kop_3       [c/ko_3]]
    ^{:doc/actions [{}]}                                                                           [c/kop_4       [c/ko_4]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` % `"}]}          [c/kop_5       [c/k_sp c/ks_5 c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ^ `"}]}          [c/kop_6       [c/k_sp c/ks_6 c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` & `"}]}          [c/kop_7       [c/k_sp c/ks_7 c/k_sp]]
    ^{:doc/actions [{}]}                                                                           [c/kop_8       [c/ko_8]]
    ^{:doc/actions [{}]}                                                                           [c/kop_9       [c/ko_9]]
    ^{:doc/actions [{}]}                                                                           [c/kop_0       [c/ko_0]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` - `"}]}          [c/kop_hy      [c/k_sp c/k_hy c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` + `"}]}          [c/kop_eq      [c/k_sp c/ks_eq c/k_sp]]

    ^{:doc/actions [{}]}                                                                           [c/kosp_1      [c/kos_1]]
    ^{:doc/actions [{}]}                                                                           [c/kosp_2      [c/kos_2]]
    ^{:doc/actions [{}]}                                                                           [c/kosp_3      [c/kos_3]]
    ^{:doc/actions [{}]}                                                                           [c/kosp_4      [c/kos_4]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .% `"}]}         [c/kosp_5      [c/k_sp c/k_pe c/ks_5 c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .^ `"}]}         [c/kosp_6      [c/k_sp c/k_pe c/ks_6 c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .& `"}]}         [c/kosp_7      [c/k_sp c/k_pe c/ks_7 c/k_sp]]
    ^{:doc/actions [{}]}                                                                           [c/kosp_8      [c/kos_8]]
    ^{:doc/actions [{}]}                                                                           [c/kosp_9      [c/kos_9]]
    ^{:doc/actions [{}]}                                                                           [c/kosp_0      [c/kos_0]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .- `"}]}         [c/kosp_hy     [c/k_sp c/k_pe c/k_hy c/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .+ `"}]}         [c/kosp_eq     [c/k_sp c/k_pe c/ks_eq c/k_sp]]

    ; TODO: launch float pane with editor (hx / mc); independent, or with reference
    ; alphabetic glyphs
    ^{:doc/actions [{:program c/hc,    :action "format",             :exec hc-format}]}            [c/kop_a       [:!Of1]        c/term]
    ^{:doc/actions [{}]}                                                                           [c/kop_b       [:!Of2]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "copy",               :exec hc-copy}
                    {:program c/mc,    :action "copy",               :exec mc-copy}]}              [c/kop_c       [:!Of4]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi",        :exec hc-spawn-multi}
                    {:program c/mc,    :action "spawn multi",        :exec mc-spawn-multi}]}       [c/kop_d       [:!Of5]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "toggle comments",    :exec hc-toggle-com}
                    {:program c/mc,    :action "toggle comments",    :exec mc-toggle-com}]}        [c/kop_e       [:!Of6]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "search",             :exec hc-search}
                    {:program c/mc,    :action "search",             :exec mc-search}]}            [c/kop_f       [:!Of7]        c/term]
    ^{:doc/actions [{}]}                                                                           [c/kop_g       [:!Of8]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "find prev",          :exec hc-find-prev}
                    {:program c/mc,    :action "find prev",          :exec mc-find-prev}]}         [c/kop_h       [:!Of9]        c/term]
    ^{:doc/actions [{}]}                                                                           [c/kop_i       [:!Of10]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi down",   :exec hc-spawn-down}
                    {:program c/mc,    :action "spawn multi down",   :exec mc-spawn-down}]}        [c/kop_j       [:!Tf1]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi up",     :exec hc-spawn-up}
                    {:program c/mc,    :action "spawn multi up",     :exec mc-spawn-up}]}          [c/kop_k       [:!Tf2]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "find next",          :exec hc-find-next}
                    {:program c/mc,    :action "find next",          :exec mc-find-next}]}         [c/kop_l       [:!Tf4]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "toggle macro",       :exec hc-record}
                    {:program c/mc,    :action "toggle macro",       :exec mc-record}]}            [c/kop_m       [:!Tf5]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "new buffer",         :exec hc-new}
                    {:program c/mc,    :action "new buffer",         :exec mc-new}]}               [c/kop_n       [:!Tf6]        c/term]
    ^{:doc/actions [{}]}                                                                           [c/kop_o       [:!Tf7]        c/term]
    ^{:doc/actions [{}]}                                                                           [c/kop_p       [:!Tf8]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "quit",               :exec hc-quit}
                    {:program c/mc,    :action "quit",               :exec mc-quit}]}              [c/kop_q       [:!Tf9]        c/term]
    ^{:doc/actions [{:program c/hc,    :action "reload buffers",     :exec hc-reload}]}            [c/kop_r       [:!Tf10]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "save & quit",        :exec hc-write-quit}
                    {:program c/mc,    :action "save & quit",        :exec mc-write-quit}]}        [c/kop_s       [:!OTf1]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "compile todo",       :exec hc-todor-compile}]}     [c/kop_t       [:!OTf2]       c/term]
    ^{:doc/actions [{}]}                                                                           [c/kop_u       [:!OTf4]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "paste",              :exec hc-paste}
                    {:program c/mc,    :action "paste",              :exec mc-paste}]}             [c/kop_v       [:!OTf5]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "save",               :exec hc-write}
                    {:program c/mc,    :action "save",               :exec mc-write}]}             [c/kop_w       [:!OTf6]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "cut",                :exec hc-cut}
                    {:program c/mc,    :action "cut",                :exec mc-cut}]}               [c/kop_x       [:!OTf7]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "skip multi",         :exec hc-skip-multi}
                    {:program c/mc,    :action "skip multi",         :exec mc-skip-multi}]}        [c/kop_y       [:!OTf8]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "remove multi",       :exec hc-rm-multi}
                    {:program c/mc,    :action "remove multi",       :exec mc-rm-multi}]}          [c/kop_z       [:!OTf9]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "command mode",       :exec hc-cmd}
                    {:program c/mc,    :action "command mode",       :exec mc-cmd}]}               [c/kop_rt      [:!OTf10]      c/term]

    ^{:doc/actions [{:program c/hc,    :action "select all",         :exec hc-select-all}
                    {:program c/mc,    :action "select all",         :exec mc-select-all}]}        [c/kosp_a      [:!OSf1]       c/term]
    ^{:doc/actions [{}]}                                                                           [c/kosp_b      [:!OSf2]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "copy line",          :exec hc-copy-line}
                    {:program c/mc,    :action "copy line",          :exec mc-copy-line}]}         [c/kosp_c      [:!OSf4]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "duplicate line",     :exec hc-dup-line}
                    {:program c/mc,    :action "duplicate line",     :exec mc-dup-line}]}          [c/kosp_d      [:!OSf5]       c/term]
    ^{:doc/actions [{}]}                                                                           [c/kosp_e      [:!OSf6]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "global search",      :exec hc-global-search}
                    {:program c/mc,    :action "global search",      :exec mc-global-search}]}     [c/kosp_f      [:!OSf7]       c/term]
    ^{:doc/actions [{}]}                                                                           [c/kosp_g      [:!OSf8]       c/term]
    ^{:doc/actions [{}]}                                                                           [c/kosp_h      [:!OSf9]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "indent",             :exec hc-indent}
                    {:program c/mc,    :action "indent",             :exec mc-indent}]}            [c/kosp_i      [:!OSf10]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "move down",          :exec hc-line-down}
                    {:program c/mc,    :action "move down",          :exec mc-line-down}]}         [c/kosp_j      [:!TSf1]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "move up",            :exec hc-line-up}
                    {:program c/mc,    :action "move up",            :exec mc-line-up}]}           [c/kosp_k      [:!TSf2]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "select line",        :exec hc-select-line}
                    {:program c/mc,    :action "select line",        :exec mc-select-line}]}       [c/kosp_l      [:!TSf4]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "play macro",         :exec hc-play}
                    {:program c/mc,    :action "play macro",         :exec mc-play}]}              [c/kosp_m      [:!TSf5]       c/term]
    ^{:doc/actions [{}]}                                                                           [c/kosp_n      [:!TSf6]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "unindent",           :exec hc-unindent}
                    {:program c/mc,    :action "unindent",           :exec mc-unindent}]}          [c/kosp_o      [:!TSf7]       c/term]
    ^{:doc/actions [{}]}                                                                           [c/kosp_p      [:!TSf8]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch lazygit",     :exec hc-lazygit}]}           [c/kosp_q      [:!TSf9]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch serpl",       :exec hc-serpl}]}             [c/kosp_r      [:!TSf10]      c/term]
    ^{:doc/actions [{}]}                                                                           [c/kosp_s      [:!OTSf1]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "interactive todo",   :exec hc-todor-interactive}]} [c/kosp_t      [:!OTSf2]      c/term]
    ^{:doc/actions [{}]}                                                                           [c/kosp_u      [:!OTSf4]      c/term]
    ^{:doc/actions [{}]}                                                                           [c/kosp_v      [:!OTSf5]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch watch",       :exec hc-watch}]}             [c/kosp_w      [:!OTSf6]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "cut line",           :exec hc-cut-line}
                    {:program c/mc,    :action "cut line",           :exec mc-cut-line}]}          [c/kosp_x      [:!OTSf7]      c/term]
    ^{:doc/actions [{}]}                                                                           [c/kosp_y      [:!OTSf8]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "yank diagnostic",    :exec hc-copy-diag}]}         [c/kosp_z      [:!OTSf9]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch shell",       :exec hc-shell}
                    {:program c/mc,    :action "shell mode",         :exec mc-shell}]}             [c/kosp_rt     [:!OTSf10]     c/term]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lopt)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
