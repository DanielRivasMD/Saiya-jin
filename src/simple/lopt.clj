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
            [config.function :as f]

            [config.helix :as hx]
            [config.lazygit :as lg]
            [config.micro :as mc]
            [config.serpl :as sr]
            [config.zellij :as zj]
))

(def out-file "lopt.edn")

(defn lopt []

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  {:des "Option Mode"
   :rules
   [;
    ; arrow glyphs
    ^{:doc/actions [{:program c/hc,    :action "goto prev word",     :exec hx/prev-word}]}         [r/kop_al      [b/ko_b]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto next word",     :exec hx/next-word}]}         [r/kop_ar      [b/ko_f]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto prev para",     :exec hx/prev-para}
                    {:program c/lg,    :action "goto prev page",     :exec lg/prev-page}
                    {:program c/mc,    :action "goto prev para",     :exec mc/prev-para}
                    {:program c/ze,    :action "half page up",       :exec zj/e-half-up}]}         [r/kop_au      [r/k_pu]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "goto next para",     :exec hx/next-para}
                    {:program c/lg,    :action "goto next page",     :exec lg/next-page}
                    {:program c/mc,    :action "goto next para",     :exec mc/next-para}
                    {:program c/ze,    :action "half page down",     :exec zj/e-half-down}]}       [r/kop_ad      [r/k_pd]       c/term]

    ^{:doc/actions [{:program c/hc,    :action "select prev word",   :exec hx/select-prev-word}
                    {:program c/mc,    :action "select prev word",   :exec mc/select-prev-word}]}  [r/kosp_al     [r/kos_al]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "select next word",   :exec hx/select-next-word}
                    {:program c/mc,    :action "select next word",   :exec mc/select-next-word}]}  [r/kosp_ar     [r/kos_ar]     c/term]
    ^{:doc/actions [{:program c/hi,    :action "select prev para",   :exec hx/i-select-prev-para}
                    {:program c/hn,    :action "select prev para",   :exec hx/n-select-prev-para}
                    {:program c/hs,    :action "select prev para",   :exec hx/s-select-prev-para}
                    {:program c/mc,    :action "select prev para",   :exec mc/select-prev-para}]}  [r/kosp_au     [r/kos_au]     c/term]
    ^{:doc/actions [{:program c/hi,    :action "select next para",   :exec hx/i-select-next-para}
                    {:program c/hn,    :action "select next para",   :exec hx/n-select-next-para}
                    {:program c/hs,    :action "select next para",   :exec hx/s-select-next-para}
                    {:program c/mc,    :action "select next para",   :exec mc/select-next-para}]}  [r/kosp_ad     [r/kos_ad]     c/term]

    ; technical glyphs
    ^{:doc/actions [{:program c/tm,    :action "less or equal",      :sequence "` <= `"}]}         [t/kop_ob      [a/k_sp t/ks_cm n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "more or equal",      :sequence "` >= `"}]}         [t/kop_cb      [a/k_sp t/ks_pe n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "OR",                 :sequence "` | `"}]}          [t/kop_sc      [a/k_sp t/ks_bl a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "AND",                :sequence "` & `"}]}          [t/kop_qu      [a/k_sp n/ks_7 a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "compare",            :sequence "` == `"}]}         [t/kop_bl      [a/k_sp n/k_eq n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "less than",          :sequence "` < `"}]}          [t/kop_cm      [a/k_sp t/ks_cm a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "more than",          :sequence "` > `"}]}          [t/kop_pe      [a/k_sp t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terniary compare",   :sequence "` ? `"}]}          [t/kop_sl      [a/k_sp t/ks_sl a/k_sp]]

    ^{:doc/actions [{:program c/tm,    :action "dot less or equal",  :sequence "` .<= `"}]}        [t/kosp_ob     [a/k_sp t/k_pe t/ks_cm n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "dot more or equal",  :sequence "` .>= `"}]}        [t/kosp_cb     [a/k_sp t/k_pe t/ks_pe n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "double OR",          :sequence "` || `"}]}         [t/kosp_sc     [a/k_sp t/ks_bl t/ks_bl a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "double AND",         :sequence "` && `"}]}         [t/kosp_qu     [a/k_sp n/ks_7 n/ks_7 a/k_sp]]
    ^{:doc/actions [{:program c/tm     :action "dot compare",        :sequence "` .== `"}]}        [t/kosp_bl     [a/k_sp t/k_pe n/k_eq n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "dot less than",      :sequence "` .< `"}]}         [t/kosp_cm     [a/k_sp t/k_pe t/ks_cm a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "dot more than",      :sequence "` .> `"}]}         [t/kosp_pe     [a/k_sp t/k_pe t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "terniary clause",    :sequence "` : `"}]}          [t/kosp_sl     [a/k_sp t/ks_sc a/k_sp]]

    ; action glyphs
    ^{:doc/actions [{:program c/tm,    :action "NOT equal",          :sequence "` != `"}]}         [a/kop_db      [a/k_sp n/ks_1 n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "assign",             :sequence "` = `"}]}          [a/kop_re      [a/k_sp n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "julia pipe",         :sequence "` |> `"}]}         [a/kop_rs      [a/k_sp t/ks_bl t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "multiply",           :sequence "` * `"}]}          [a/kop_ro      [a/k_sp n/ks_8 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "divide",             :sequence "` / `"}]}          [a/kop_rc      [a/k_sp t/k_sl a/k_sp]]
    ^{:doc/actions [{:program c/alf,   :action "prompt"}]}                                         [a/kop_sp      [c/koc_lock]]

    ^{:doc/actions [{:program c/tm,    :action "dot NOT equal",      :sequence "` .!= `"}]}        [a/kosp_db     [a/k_sp t/k_pe n/ks_1 n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "dot assign",         :sequence "` .= `"}]}         [a/kosp_re     [a/k_sp t/k_pe n/k_eq a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "dot julia pipe",     :sequence "` .|> `"}]}        [a/kosp_rs     [a/k_sp t/k_pe t/ks_bl t/ks_pe a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "dot multiply",       :sequence "` .* `"}]}         [a/kosp_ro     [a/k_sp t/k_pe n/ks_8 a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "dot divide",         :sequence "` ./ `"}]}         [a/kosp_rc     [a/k_sp t/k_pe t/k_sl a/k_sp]]
    ^{:doc/actions [{:program c/alf,   :action "file prompt"}]}                                    [a/kosp_sp     [c/kocs_lock]]

    ; numeric glyphs
    ^{:doc/actions [{}]}                                                                           [n/kop_1       [n/ko_1]]
    ^{:doc/actions [{}]}                                                                           [n/kop_2       [n/ko_2]]
    ^{:doc/actions [{}]}                                                                           [n/kop_3       [n/ko_3]]
    ^{:doc/actions [{}]}                                                                           [n/kop_4       [n/ko_4]]
    ^{:doc/actions [{}]}                                                                           [n/kop_5       [n/ko_5]]
    ^{:doc/actions [{}]}                                                                           [n/kop_6       [n/ko_6]]
    ^{:doc/actions [{}]}                                                                           [n/kop_7       [n/ko_7]]
    ^{:doc/actions [{}]}                                                                           [n/kop_8       [n/ko_8]]
    ^{:doc/actions [{}]}                                                                           [n/kop_9       [n/ko_9]]
    ^{:doc/actions [{}]}                                                                           [n/kop_0       [n/ko_0]]
    ^{:doc/actions [{:program c/tm,    :action "substract",          :sequence "` - `"}]}          [n/kop_hy      [a/k_sp n/k_hy a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "add",                :sequence "` + `"}]}          [n/kop_eq      [a/k_sp n/ks_eq a/k_sp]]

    ^{:doc/actions [{}]}                                                                           [n/kosp_1      [n/kos_1]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_2      [n/kos_2]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_3      [n/kos_3]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_4      [n/kos_4]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_5      [n/kos_5]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_6      [n/kos_6]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_7      [n/kos_7]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_8      [n/kos_8]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_9      [n/kos_9]]
    ^{:doc/actions [{}]}                                                                           [n/kosp_0      [n/kos_0]]
    ^{:doc/actions [{:program c/tm,    :action "dot substract",      :sequence "` .- `"}]}         [n/kosp_hy     [a/k_sp t/k_pe n/k_hy a/k_sp]]
    ^{:doc/actions [{:program c/tm,    :action "dot add",            :sequence "` .+ `"}]}         [n/kosp_eq     [a/k_sp t/k_pe n/ks_eq a/k_sp]]

    ; alphabetic glyphs
    ^{:doc/actions [{:program c/hc,    :action "format",             :exec hx/sh-format}]}         [b/kop_a       [b/ko_a]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "git blame",          :exec hx/git-blame}]}         [b/kop_b       [f/ko_f1]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "copy",               :exec hx/copy}
                    {:program c/mc,    :action "copy",               :exec mc/copy}]}              [b/kop_c       [f/ko_f2]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi",        :exec hx/spawn-multi}
                    {:program c/mc,    :action "spawn multi",        :exec mc/spawn-multi}]}       [b/kop_d       [f/ko_f4]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "toggle comments",    :exec hx/toggle-comment}
                    {:program c/mc,    :action "toggle comments",    :exec mc/comment-mc}]}        [b/kop_e       [b/ko_e]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "search",             :exec hx/search}
                    {:program c/mc,    :action "search",             :exec mc/search}]}            [b/kop_f       [f/ko_f5]      c/term]
    ^{:doc/actions [{}]}                                                                           [b/kop_g       [b/ko_g]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "find prev",          :exec hx/find-prev}
                    {:program c/mc,    :action "find prev",          :exec mc/find-prev}]}         [b/kop_h       [b/ko_h]       c/term]
    ^{:doc/actions [{}]}                                                                           [b/kop_i       [b/ko_i]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi down",   :exec hx/spawn-down}
                    {:program c/mc,    :action "spawn multi down",   :exec mc/spawn-down}]}        [b/kop_j       [b/ko_j]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "spawn multi up",     :exec hx/spawn-up}
                    {:program c/mc,    :action "spawn multi up",     :exec mc/spawn-up}]}          [b/kop_k       [b/ko_k]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "find next",          :exec hx/find-next}
                    {:program c/mc,    :action "find next",          :exec mc/find-next}]}         [b/kop_l       [b/ko_l]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "toggle macro",       :exec hx/record}
                    {:program c/mc,    :action "toggle macro",       :exec mc/record}]}            [b/kop_m       [b/ko_m]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "new buffer",         :exec hx/new}
                    {:program c/mc,    :action "new buffer",         :exec mc/new}]}               [b/kop_n       [b/ko_n]       c/term]
    ^{:doc/actions [{}]}                                                                           [b/kop_o       [f/ko_f6]      c/term]
    ^{:doc/actions [{}]}                                                                           [b/kop_p       [b/ko_p]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "quit",               :exec hx/quit}
                    {:program c/mc,    :action "quit",               :exec mc/quit}
                    {:program c/sr,    :action "quit",               :exec sr/quit}]}              [b/kop_q       [b/ko_q]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "reload buffers",     :exec hx/reload}]}            [b/kop_r       [b/ko_r]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "save & quit",        :exec hx/write-quit}
                    {:program c/mc,    :action "save & quit",        :exec mc/write-quit}]}        [b/kop_s       [b/ko_s]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "compile todo",       :exec hx/todor-compile}]}     [b/kop_t       [b/ko_t]       c/term]
    ^{:doc/actions [{}]}                                                                           [b/kop_u       [b/ko_u]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "paste",              :exec hx/paste}
                    {:program c/mc,    :action "paste",              :exec mc/paste}]}             [b/kop_v       [b/ko_v]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "save",               :exec hx/write}
                    {:program c/mc,    :action "save",               :exec mc/write}]}             [b/kop_w       [b/ko_w]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "cut",                :exec hx/cut}
                    {:program c/mc,    :action "cut",                :exec mc/cut}]}               [b/kop_x       [b/ko_x]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "skip multi",         :exec hx/skip-multi}
                    {:program c/mc,    :action "skip multi",         :exec mc/skip-multi}]}        [b/kop_y       [b/ko_y]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "remove multi",       :exec hx/rm-multi}
                    {:program c/mc,    :action "remove multi",       :exec mc/rm-multi}]}          [b/kop_z       [b/ko_z]       c/term]
    ^{:doc/actions [{:program c/hc,    :action "command mode",       :exec hx/cmd}
                    {:program c/mc,    :action "command mode",       :exec mc/cmd}]}               [b/kop_rt      [f/ko_f10]     c/term]

    ^{:doc/actions [{:program c/hc,    :action "select all",         :exec hx/select-all}
                    {:program c/mc,    :action "select all",         :exec mc/select-all}]}        [b/kosp_a      [b/kos_a]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "monitor system",     :exec hx/btm}]}               [b/kosp_b      [f/kos_f1]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "copy line",          :exec hx/copy-line}
                    {:program c/mc,    :action "copy line",          :exec mc/copy-line}]}         [b/kosp_c      [f/kos_f2]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "duplicate line",     :exec hx/dup-line}
                    {:program c/mc,    :action "duplicate line",     :exec mc/dup-line}]}          [b/kosp_d      [f/kos_f4]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "file explorer",      :exec hx/yazi}]}              [b/kosp_e      [b/kos_e]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "global search",      :exec hx/global-search}
                    {:program c/mc,    :action "global search",      :exec mc/global-search}]}     [b/kosp_f      [f/kos_f5]     c/term]
    ^{:doc/actions [{:program c/hc,    :action "git diff",           :exec hx/git-diff}]}          [b/kosp_g      [b/kos_g]      c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_h      [b/kos_h]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "indent",             :exec hx/indent}
                    {:program c/mc,    :action "indent",             :exec mc/indent}]}            [b/kosp_i      [b/kos_i]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "move down",          :exec hx/line-down}
                    {:program c/mc,    :action "move down",          :exec mc/line-down}]}         [b/kosp_j      [b/kos_j]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "move up",            :exec hx/line-up}
                    {:program c/mc,    :action "move up",            :exec mc/line-up}]}           [b/kosp_k      [b/kos_k]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "select line",        :exec hx/select-line}
                    {:program c/mc,    :action "select line",        :exec mc/select-line}]}       [b/kosp_l      [b/kos_l]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "play macro",         :exec hx/play}
                    {:program c/mc,    :action "play macro",         :exec mc/play}]}              [b/kosp_m      [b/kos_m]      c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_n      [b/kos_n]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "unindent",           :exec hx/unindent}
                    {:program c/mc,    :action "unindent",           :exec mc/unindent}]}          [b/kosp_o      [f/kos_f6]     c/term]
    ^{:doc/actions [{:program c/hi,    :action "select para",        :exec hx/i-select-para}
                    {:program c/hn,    :action "select para",        :exec hx/n-select-para}
                    {:program c/hs,    :action "select para",        :exec hx/s-select-para}]}       [b/kosp_p      [b/kos_p]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch lazygit",     :exec hx/lazygit}]}           [b/kosp_q      [b/kos_q]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch serpl",       :exec hx/serpl}]}             [b/kosp_r      [b/kos_r]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch install",     :exec hx/install}]}           [b/kosp_s      [b/kos_s]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "interactive todo",   :exec hx/todor-interactive}]} [b/kosp_t      [b/kos_t]      c/term]
    ^{:doc/actions [{}]}                                                                           [b/kosp_u      [b/kos_u]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "paste in place",     :exec hx/paste-in-place}]}    [b/kosp_v      [b/kos_v]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch watch",       :exec hx/watch}]}             [b/kosp_w      [b/kos_w]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "cut line",           :exec hx/cut-line}
                    {:program c/mc,    :action "cut line",           :exec mc/cut-line}]}          [b/kosp_x      [b/kos_x]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "yank diagnostic",    :exec hx/copy-diag}]}         [b/kosp_y      [b/kos_y]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "copy buffer",        :exec hx/buffer-copy}]}       [b/kosp_z      [b/kos_z]      c/term]
    ^{:doc/actions [{:program c/hc,    :action "launch shell",       :exec hx/shell}
                    {:program c/mc,    :action "shell mode",         :exec mc/shell}]}             [b/kosp_rt     [f/kos_f10]     c/term]
    ]})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  (with-open [w (io/writer (str c/edn-path out-file))]
    (binding [*out* w
              *print-meta* true
              *print-namespace-maps* false]
      (pp/pprint (lopt)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
