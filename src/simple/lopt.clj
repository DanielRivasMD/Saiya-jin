;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; OPT
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns simple.lopt
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [config :as c]))

(def out-file "lopt.edn")

(def hc-prev-word        ["move_prev_word_start", "collapse_selection"])
(def hc-next-word        ["move_next_word_end", "collapse_selection"])

(def hc-prev-para        ["goto_prev_paragraph"  "collapse_selection"])
(def lg-prev-page        ["prevPage"])
(def mc-prev-para        ["ParagraphPrevious"])
(def ze-half-up          ["HalfPageScrollUp"])
(def hc-next-para        ["goto_next_paragraph"  "collapse_selection"])
(def lg-next-page        ["nextPage"])
(def mc-next-para        ["ParagraphNext"])
(def ze-half-down        ["HalfPageScrollDown"])

(def hc-select-prev-word ["extend_prev_word_start"])
(def mc-select-prev-word ["SelectWordLeft"])
(def hc-select-next-word ["extend_next_word_end"])
(def mc-select-next-word ["SelectWordRight"])

(def hi-select-prev-para ["select_mode" "goto_prev_paragraph" "insert_mode"])
(def hn-select-prev-para ["select_mode" "goto_prev_paragraph" "normal_mode"])
(def hs-select-prev-para ["select_mode" "goto_prev_paragraph" "select_mode"])
(def mc-select-prev-para ["StartOfLine,SelectLine,ParagraphPrevious,SelectWordRight"])

(def hi-select-next-para ["select_mode" "goto_next_paragraph" "append_mode"])
(def hn-select-next-para ["select_mode" "goto_next_paragraph" "normal_mode"])
(def hs-select-next-para ["select_mode" "goto_next_paragraph" "select_mode"])
(def mc-select-next-para ["StartOfLine,SelectLine,ParagraphNext,SelectWordRight"])

(def hc-format           [":format"])
(def hc-copy             ["yank_to_clipboard"])
(def mc-copy             ["Copy"])
(def hc-spawn-multi      ["search_selection", "extend_search_next"])
(def mc-spawn-multi      ["SpawnMultiCursor"])
(def hc-toggle-com       ["toggle_comments"])
(def mc-toggle-com       ["lua:comment.comment"])
(def hc-search           ["search"])
(def mc-search           ["FindLiteral"])
(def hc-find-prev        ["search_prev"])
(def mc-find-prev        ["FindPrevious"])
(def hc-spawn-down       ["normal_mode", "copy_selection_on_next_line", "MODE"])
(def mc-spawn-down       ["SpawnMultiCursorDown"])
(def hc-spawn-up         ["normal_mode", "copy_selection_on_prev_line", "MODE"])
(def mc-spawn-up         ["SpawnMultiCursorUp"])
(def hc-find-next        ["search_next"])
(def mc-find-next        ["FindNext"])
(def hc-record           ["record_macro"])
(def mc-record           ["ToggleMacro"])
(def hc-new              [":new"])
(def mc-new              ["AddTab"])
(def hc-quit             ["normal_mode", ":quit-all"])
(def mc-quit             ["QuitAll"])
(def hc-reload           ["normal_mode", ":reload-all", "MODE"])
(def hc-write-quit       ["normal_mode", ":write-quit-all"])
(def mc-write-quit       ["Save,QuitAll"])
(def hc-paste            ["paste_clipboard_after"])
(def mc-paste            ["Paste"])
(def hc-write            ["normal_mode", ":write"])
(def mc-write            ["Save"])
(def hc-cut              ["yank_to_clipboard", "delete_selection_noyank"])
(def mc-cut              ["Cut"])
(def hc-skip-multi       ["search_selection", "search_next"])
(def mc-skip-multi       ["SkipMultiCursor"])
(def hc-rm-multi         ["search_selection", "search_prev"])
(def mc-rm-multi         ["RemoveMultiCursor"])
(def hc-cmd              ["command_mode"])
(def mc-cmd              ["CommandMode"])

; TODO: patch float coors for font size 14
(def hc-select-all       ["select_all"])
(def mc-select-all       ["SelectAll"])
(def hc-copy-line        ["extend_to_line_bounds", "yank_to_clipboard"])
(def mc-copy-line        ["CopyLine"])
(def hc-dup-line         ["extend_to_line_bounds", "yank", "paste_after", "goto_line_start"])
(def mc-dup-line         ["DuplicateLine"])
(def hc-global-search    ["global_search"])
(def mc-global-search    ["Find"])
(def hc-indent           ["indent"])
(def mc-indent           ["IndentSelection"])
(def hc-line-down        ["normal_mode", "extend_to_line_bounds", "delete_selection", "paste_after", "MODE"])
(def mc-line-down        ["MoveLinesDown"])
(def hc-line-up          ["normal_mode", "extend_to_line_bounds", "delete_selection", "move_line_up", "paste_before", "MODE"])
(def mc-line-up          ["MoveLinesUp"])
(def hc-select-line      ["goto_line_end", "select_mode", "goto_line_start", "MODE"])
(def mc-select-line      ["SelectLine"])
(def hc-play             ["replay_macro"])
(def mc-play             ["PlayMacro"])
(def hc-unindent         ["unindent"])
(def mc-unindent         ["OutdentSelection"])
(def hc-lazygit          [":sh zellij run --name lazygit --pinned true  --close-on-exit --floating  --width 180 --height  90 --x   0 --y   0 -- lazygit"])
(def hc-serpl            [":sh zellij run --name serpl   --pinned true  --close-on-exit --floating  --width 180 --height  90 --x   0 --y   0 -- serpl"])
(def hc-todor            [":sh just todor"])
(def hc-watch            [":sh zellij run --name canvas  --pinned true  --close-on-exit --floating  --width  45 --height  20 --x 135 --y   0 -- just watch"])
(def hc-cut-line         ["extend_to_line_bounds", "delete_selection_noyank"])
(def mc-cut-line         ["CutLine"])
(def hc-copy-diag        [":yank-diagnostic"])
(def hc-shell            [":sh zellij run --name canvas  --pinned false --close-on-exit --floating  --width 180 --height  90 --x   0 --y   0 -- zsh"])
(def mc-shell            ["ShellMode"])

(defn lopt []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "goto prev word",     :exec hc-prev-word}]}         [(c/mk c/bop c/al)  [:!Ob]     :term]
    ^{:doc/actions [{:program c/hc,    :action "goto next word",     :exec hc-next-word}]}         [(c/mk c/bop c/ar)  [:!Of]     :term]
    ^{:doc/actions [{:program c/hc,    :action "goto prev para",     :exec hc-prev-para}
                    {:program c/lg,    :action "goto prev page",     :exec lg-prev-page}
                    {:program c/mc,    :action "goto prev para",     :exec mc-prev-para}
                    {:program c/ze,    :action "half page up",       :exec ze-half-up}]}           [(c/mk c/bop c/au)  [c/kpu]    :term]
    ^{:doc/actions [{:program c/hc,    :action "goto next para",     :exec hc-next-para}
                    {:program c/lg,    :action "goto next page",     :exec lg-next-page}
                    {:program c/mc,    :action "goto next para",     :exec mc-next-para}
                    {:program c/ze,    :action "half page down",     :exec ze-half-down}]}         [(c/mk c/bop c/ad)  [c/kpd]    :term]

    ^{:doc/actions [{:program c/hc,    :action "select prev word",   :exec hc-select-prev-word}
                    {:program c/mc,    :action "select prev word",   :exec mc-select-prev-word}]}  [(c/mk c/bosp c/al) [c/kosal]  :term]
    ^{:doc/actions [{:program c/hc,    :action "select next word",   :exec hc-select-next-word}
                    {:program c/mc,    :action "select next word",   :exec mc-select-next-word}]}  [(c/mk c/bosp c/ar) [c/kosar]  :term]
    ^{:doc/actions [{:program c/hi,    :action "select prev para",   :exec hi-select-prev-para}
                    {:program c/hn,    :action "select prev para",   :exec hn-select-prev-para}
                    {:program c/hs,    :action "select prev para",   :exec hs-select-prev-para}
                    {:program c/mc,    :action "select prev para",   :exec mc-select-prev-para}]}  [(c/mk c/bosp c/au) [c/kosau]  :term]
    ^{:doc/actions [{:program c/hi,    :action "select next para",   :exec hi-select-next-para}
                    {:program c/hn,    :action "select next para",   :exec hn-select-next-para}
                    {:program c/hs,    :action "select next para",   :exec hs-select-next-para}
                    {:program c/mc,    :action "select next para",   :exec mc-select-next-para}]}  [(c/mk c/bosp c/ad) [c/kosad]  :term]

    ; TODO: annotate sequences
    ; technical glyphs
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` <= `"}]}         [(c/mk c/bop c/ob)  [:spacebar :!Scomma :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` >= `"}]}         [(c/mk c/bop c/cb)  [:spacebar :!Speriod :equal_sign :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/bop c/sc)      [(c/mk c/bo c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/bop c/qu)      [(c/mk c/bo c/qu)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` | `"}]}          [(c/mk c/bop c/bl)  [:spacebar :!Sbackslash :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` < `"}]}          [(c/mk c/bop c/cm)  [:spacebar :!Scomma :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` > `"}]}          [(c/mk c/bop c/pe)  [:spacebar :!Speriod :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ? `"}]}          [(c/mk c/bop c/sl)  [:spacebar :!Sslash :spacebar]]

    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .<= `"}]}        [(c/mk c/bosp c/ob) [:spacebar :period :!Scomma :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .>= `"}]}        [(c/mk c/bosp c/cb) [:spacebar :period :!Speriod :equal_sign :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/bosp c/sc)     [(c/mk c/bos c/sc)]]
    ^{:doc/actions [{}]} [(c/mk c/bosp c/qu)     [(c/mk c/bos c/qu)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .| `"}]}         [(c/mk c/bosp c/bl) [:spacebar :period :!Sbackslash :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .< `"}]}         [(c/mk c/bosp c/cm) [:spacebar :period :!Scomma :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .> `"}]}         [(c/mk c/bosp c/pe) [:spacebar :period :!Speriod :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/bosp c/sl)     [(c/mk c/bos c/sl)]]

    ; action glyphs
    ^{:doc/actions [{}]} [(c/mk c/bop c/db)      [(c/mk c/bo c/db)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` = `"}]}          [(c/mk c/bop c/re)  [:spacebar :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` |> `"}]}         [(c/mk c/bop c/rs)  [:spacebar :!Sbackslash :!Speriod :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` * `"}]}          [(c/mk c/bop c/ro)  [:spacebar :!S8 :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` / `"}]}          [(c/mk c/bop c/rc)  [:spacebar :slash :spacebar]]
    ^{:doc/actions [{:program "Alfred",      :action "prompt"}]}                                   [(c/mk c/bop c/sp)  [:!OCnon_us_pound]]

    ^{:doc/actions [{}]} [(c/mk c/bosp c/db)     [(c/mk c/bosp c/db)]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .= `"}]}         [(c/mk c/bosp c/re) [:spacebar :period :equal_sign :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .|> `"}]}        [(c/mk c/bosp c/rs) [:spacebar :period :!Sbackslash :!Speriod :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .* `"}]}         [(c/mk c/bosp c/ro) [:spacebar :period :!S8 :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ./ `"}]}         [(c/mk c/bosp c/rc) [:spacebar :period :slash :spacebar]]
    ^{:doc/actions [{:program "Alfred",      :action "file prompt"}]}                              [(c/mk c/bosp c/sp) [:!OCSnon_us_pound]]

    ; numeric glyphs
    ^{:doc/actions [{}]} [(c/mk c/bop "1")       [(c/mk c/bo "1")]]
    ^{:doc/actions [{}]} [(c/mk c/bop "2")       [(c/mk c/bo "2")]]
    ^{:doc/actions [{}]} [(c/mk c/bop "3")       [(c/mk c/bo "3")]]
    ^{:doc/actions [{}]} [(c/mk c/bop "4")       [(c/mk c/bo "4")]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` % `"}]}          [(c/mk c/bop "5")   [:spacebar :!S5 :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` ^ `"}]}          [(c/mk c/bop "6")   [:spacebar :!S6 :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` & `"}]}          [(c/mk c/bop "7")   [:spacebar :!S7 :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/bop "8")       [(c/mk c/bo "8")]]
    ^{:doc/actions [{}]} [(c/mk c/bop "9")       [(c/mk c/bo "9")]]
    ^{:doc/actions [{}]} [(c/mk c/bop "0")       [(c/mk c/bo "0")]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` - `"}]}          [(c/mk c/bop c/hy)  [:spacebar :hyphen :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` + `"}]}          [(c/mk c/bop c/eq)  [:spacebar :!Sequal_sign :spacebar]]

    ^{:doc/actions [{}]} [(c/mk c/bosp "1")      [(c/mk c/bos "1")]]
    ^{:doc/actions [{}]} [(c/mk c/bosp "2")      [(c/mk c/bos "2")]]
    ^{:doc/actions [{}]} [(c/mk c/bosp "3")      [(c/mk c/bos "3")]]
    ^{:doc/actions [{}]} [(c/mk c/bosp "4")      [(c/mk c/bos "4")]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .% `"}]}         [(c/mk c/bosp "5")  [:spacebar :period :!S5 :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .^ `"}]}         [(c/mk c/bosp "6")  [:spacebar :period :!S6 :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .& `"}]}         [(c/mk c/bosp "7")  [:spacebar :period :!S7 :spacebar]]
    ^{:doc/actions [{}]} [(c/mk c/bosp "8")      [(c/mk c/bos "8")]]
    ^{:doc/actions [{}]} [(c/mk c/bosp "9")      [(c/mk c/bos "9")]]
    ^{:doc/actions [{}]} [(c/mk c/bosp "0")      [(c/mk c/bos "0")]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .- `"}]}         [(c/mk c/bosp c/hy) [:spacebar :period :hyphen :spacebar]]
    ^{:doc/actions [{:program c/tm,    :action "terminal",           :sequence "` .+ `"}]}         [(c/mk c/bosp c/eq) [:spacebar :period :!Sequal_sign :spacebar]]

    ; TODO: launch float pane with editor (hx / mc); independent, or with reference
    ; alphabetic glyphs
    ^{:doc/actions [{:program c/hc,    :action "format",             :exec hc-format}]}            [(c/mk c/bop "a")   [:!Of1]    :term]
    ^{:doc/actions [{}]} [(c/mk c/bop "b")       [:!Of2] :term]
    ^{:doc/actions [{:program c/hc,    :action "copy",               :exec hc-copy}
                    {:program c/mc,    :action "copy",               :exec mc-copy}]}              [(c/mk c/bop "c")   [:!Of4]    :term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi",        :exec hc-spawn-multi}
                    {:program c/mc,    :action "spawn multi",        :exec mc-spawn-multi}]}       [(c/mk c/bop "d")   [:!Of5]    :term]
    ^{:doc/actions [{:program c/hc,    :action "toggle comments",    :exec hc-toggle-com}
                    {:program c/mc,    :action "toggle comments",    :exec mc-toggle-com}]}        [(c/mk c/bop "e")   [:!Of6]    :term]
    ^{:doc/actions [{:program c/hc,    :action "search",             :exec hc-search}
                    {:program c/mc,    :action "search",             :exec mc-search}]}            [(c/mk c/bop "f")   [:!Of7]    :term]
    ^{:doc/actions [{}]} [(c/mk c/bop "g")       [:!Of8] :term]
    ^{:doc/actions [{:program c/hc,    :action "find prev",          :exec hc-find-prev}
                    {:program c/mc,    :action "find prev",          :exec mc-find-prev}]}         [(c/mk c/bop "h")   [:!Of9]    :term]
    ^{:doc/actions [{}]} [(c/mk c/bop "i")       [:!Of10] :term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi down",   :exec hc-spawn-down}
                    {:program c/mc,    :action "spawn multi down",   :exec mc-spawn-down}]}        [(c/mk c/bop "j")   [:!Tf1]    :term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi up",     :exec hc-spawn-up}
                    {:program c/mc,    :action "spawn multi up",     :exec mc-spawn-up}]}          [(c/mk c/bop "k")   [:!Tf2]    :term]
    ^{:doc/actions [{:program c/hc,    :action "find next",          :exec hc-find-next}
                    {:program c/mc,    :action "find next",          :exec mc-find-next}]}         [(c/mk c/bop "l")   [:!Tf4]    :term]
    ^{:doc/actions [{:program c/hc,    :action "toggle macro",       :exec hc-record}
                    {:program c/mc,    :action "toggle macro",       :exec mc-record}]}            [(c/mk c/bop "m")   [:!Tf5]    :term]
    ^{:doc/actions [{:program c/hc,    :action "new buffer",         :exec hc-new}
                    {:program c/mc,    :action "new buffer",         :exec mc-new}]}               [(c/mk c/bop "n")   [:!Tf6]    :term]
    ^{:doc/actions [{}]} [(c/mk c/bop "o")       [:!Tf7] :term]
    ^{:doc/actions [{}]} [(c/mk c/bop "p")       [:!Tf8] :term]
    ^{:doc/actions [{:program c/hc,    :action "quit",               :exec hc-quit}
                    {:program c/mc,    :action "quit",               :exec mc-quit}]}              [(c/mk c/bop "q")   [:!Tf9]    :term]
    ^{:doc/actions [{:program c/hc,    :action "reload buffers",     :exec hc-reload}]}            [(c/mk c/bop "r")   [:!Tf10]   :term]
    ^{:doc/actions [{:program c/hc,    :action "save & quit",        :exec hc-write-quit}
                    {:program c/mc,    :action "save & quit",        :exec mc-write-quit}]}        [(c/mk c/bop "s")   [:!OTf1]   :term]
    ^{:doc/actions [{}]} [(c/mk c/bop "t")       [:!OTf2] :term]
    ^{:doc/actions [{}]} [(c/mk c/bop "u")       [:!OTf4] :term]
    ^{:doc/actions [{:program c/hc,    :action "paste",              :exec hc-paste}
                    {:program c/mc,    :action "paste",              :exec mc-paste}]}             [(c/mk c/bop "v")   [:!OTf5]   :term]
    ^{:doc/actions [{:program c/hc,    :action "save",               :exec hc-write}
                    {:program c/mc,    :action "save",               :exec mc-write}]}             [(c/mk c/bop "w")   [:!OTf6]   :term]
    ^{:doc/actions [{:program c/hc,    :action "cut",                :exec hc-cut}
                    {:program c/mc,    :action "cut",                :exec mc-cut}]}               [(c/mk c/bop "x")   [:!OTf7]   :term]
    ^{:doc/actions [{:program c/hc,    :action "skip multi",         :exec hc-skip-multi}
                    {:program c/mc,    :action "skip multi",         :exec mc-skip-multi}]}        [(c/mk c/bop "y")   [:!OTf8]   :term]
    ^{:doc/actions [{:program c/hc,    :action "remove multi",       :exec hc-rm-multi}
                    {:program c/mc,    :action "remove multi",       :exec mc-rm-multi}]}          [(c/mk c/bop "z")   [:!OTf9]   :term]
    ^{:doc/actions [{:program c/hc,    :action "command mode",       :exec hc-cmd}
                    {:program c/mc,    :action "command mode",       :exec mc-cmd}]}               [(c/mk c/bop c/rt)  [:!OTf10]  :term]

    ^{:doc/actions [{:program c/hc,    :action "select all",         :exec hc-select-all}
                    {:program c/mc,    :action "select all",         :exec mc-select-all}]}        [(c/mk c/bosp "a")  [:!OSf1]   :term]
    ^{:doc/actions [{}]} [(c/mk c/bosp "b")      [:!OSf2] :term]
    ^{:doc/actions [{:program c/hc,    :action "copy line",          :exec hc-copy-line}
                    {:program c/mc,    :action "copy line",          :exec mc-copy-line}]}         [(c/mk c/bosp "c")  [:!OSf4]   :term]
    ^{:doc/actions [{:program c/hc,    :action "duplicate line",     :exec hc-dup-line}
                    {:program c/mc,    :action "duplicate line",     :exec mc-dup-line}]}          [(c/mk c/bosp "d")  [:!OSf5]   :term]
    ^{:doc/actions [{}]} [(c/mk c/bosp "e")      [:!OSf6] :term]
    ^{:doc/actions [{:program c/hc,    :action "global search",      :exec hc-global-search}
                    {:program c/mc,    :action "global search",      :exec mc-global-search}]}     [(c/mk c/bosp "f")  [:!OSf7]   :term]
    ^{:doc/actions [{}]} [(c/mk c/bosp "g")      [:!OSf8] :term]
    ^{:doc/actions [{}]} [(c/mk c/bosp "h")      [:!OSf9] :term]
    ^{:doc/actions [{:program c/hc,    :action "indent",             :exec hc-indent}
                    {:program c/mc,    :action "indent",             :exec mc-indent}]}            [(c/mk c/bosp "i")  [:!OSf10]  :term]
    ^{:doc/actions [{:program c/hc,    :action "move down",          :exec hc-line-down}
                    {:program c/mc,    :action "move down",          :exec mc-line-down}]}         [(c/mk c/bosp "j")  [:!TSf1]   :term]
    ^{:doc/actions [{:program c/hc,    :action "move up",            :exec hc-line-up}
                    {:program c/mc,    :action "move up",            :exec mc-line-up}]}           [(c/mk c/bosp "k")  [:!TSf2]   :term]
    ^{:doc/actions [{:program c/hc,    :action "select line",        :exec hc-select-line}
                    {:program c/mc,    :action "select line",        :exec mc-select-line}]}       [(c/mk c/bosp "l")  [:!TSf4]   :term]
    ^{:doc/actions [{:program c/hc,    :action "play macro",         :exec hc-play}
                    {:program c/mc,    :action "play macro",         :exec mc-play}]}              [(c/mk c/bosp "m")  [:!TSf5]   :term]
    ^{:doc/actions [{}]} [(c/mk c/bosp "n")       [:!TSf6] :term]
    ^{:doc/actions [{:program c/hc,    :action "unindent",           :exec hc-unindent}
                    {:program c/mc,    :action "unindent",           :exec mc-unindent}]}          [(c/mk c/bosp "o")  [:!TSf7]   :term]
    ^{:doc/actions [{}]} [(c/mk c/bosp "p")       [:!TSf8] :term]
    ^{:doc/actions [{:program c/hc,    :action "launch lazygit",     :exec hc-lazygit}]}           [(c/mk c/bosp "q")  [:!TSf9]   :term]
    ^{:doc/actions [{:program c/hc,    :action "launch serpl",       :exec hc-serpl}]}             [(c/mk c/bosp "r")  [:!TSf10]  :term]
    ^{:doc/actions [{}]} [(c/mk c/bosp "s")       [:!OTSf1] :term]
    ^{:doc/actions [{:program c/hc,    :action "compile todo",       :exec hc-todor}]}             [(c/mk c/bosp "t")  [:!OTSf2]  :term]
    ^{:doc/actions [{}]} [(c/mk c/bosp "u")       [:!OTSf4] :term]
    ^{:doc/actions [{}]} [(c/mk c/bosp "v")       [:!OTSf5] :term]
    ^{:doc/actions [{:program c/hc,    :action "launch watch",       :exec hc-watch}]}             [(c/mk c/bosp "w")  [:!OTSf6]  :term]
    ^{:doc/actions [{:program c/hc,    :action "cut line",           :exec hc-cut-line}
                    {:program c/mc,    :action "cut line",           :exec mc-cut-line}]}          [(c/mk c/bosp "x")  [:!OTSf7]  :term]
    ^{:doc/actions [{}]} [(c/mk c/bosp "y")       [:!OTSf8] :term]
    ^{:doc/actions [{:program c/hc,    :action "yank diagnostic",    :exec hc-copy-diag}]}         [(c/mk c/bosp "z")  [:!OTSf9]  :term]
    ^{:doc/actions [{:program c/hc,    :action "launch shell",       :exec hc-shell}
                    {:program c/mc,    :action "shell mode",         :exec mc-shell}]}             [(c/mk c/bosp c/rt) [:!OTSf10] :term]]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lopt)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
