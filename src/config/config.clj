;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; config
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns config.config)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; key maker
(defn mk [& parts]
  (keyword (apply str parts)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; outpath
(def edn-path "edn/")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; programs
(def alf "Alfred")
(def sys "System")
(def ay  "alacritty")
(def br  "broot")
(def mc  "micro")
(def hc  "helix-common")
(def hi  "helix-insert")
(def hn  "helix-normal")
(def hs  "helix-select")
(def lg  "lazygit")
(def tm  "term")
(def zj  "zellij-shared")
(def ze  "zellij-enter")
(def zx  "zellij-except")
(def zl  "zellij-lock")
(def zp  "zellij-rpane")
(def zt  "zellij-rtab")

(def browser (mk "browser"))
(def finder  (mk "finder"))
(def mail    (mk "mail"))
(def skim    (mk "skim"))
(def term    (mk "term"))
(def zoom    (mk "zoom"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; primitive modifiers
(def O "O")
(def E "E")
(def T "T")
(def W "W")
(def C "C")
(def Q "Q")
(def S "S")
(def R "R")

(def K "!")
(def H "#")
(def P (str H "P"))

(def _lock "keypad_num_lock")
(def _caps "caps_lock")
(def _esc "escape")
(def _tab "tab")
(def _us "non_us_pound")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def k_lock (mk _lock))
(def k_esc (mk _esc))
(def k_tab (mk _tab))
(def ko_tab (mk K O _tab))
(def kc_tab (mk K C _tab))

(def kp_lock (mk P _lock))
(def kh_caps (mk H H _caps))
(def kp_esc (mk P _esc))
(def kp_tab (mk P _tab))

(def kotcsp_lock (mk K O T C R P _lock))

(def koc_us (mk K O C _us))
(def kocs_us (mk K O C S _us))

(def kwp_us (mk K W P _us))

(def kewp_us (mk K E W P _us))
(def kewqp_us (mk K E W Q P _us))
(def krp_us (mk K R P _us))
(def kep_us (mk K E P _us))
(def kqp_us (mk K Q P _us))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; primitive glyphs
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; arrow glyphs
(def _pu "page_up")
(def _pd "page_down")
(def _hm "home")
(def _ed "end")

(def _au "up_arrow")
(def _ad "down_arrow")
(def _al "left_arrow")
(def _ar "right_arrow")

; technical glyphs
(def _ob "open_bracket")
(def _cb "close_bracket")
(def _sc "semicolon")
(def _qu "quote")
(def _bl "backslash")
(def _cm "comma")
(def _pe "period")
(def _sl "slash")

; action glyphs
(def _db "delete_or_backspace")
(def _re "return_or_enter")
(def _rs "right_shift")
(def _ro "right_option")
(def _rc "right_command")
(def _sp "spacebar")
(def _rt "right_control")
(def _lc "left_command")
(def _lo "left_option")
(def _lt "left_control")
(def _ls "left_shift")

; numeric glyphs
(def _1 "1")
(def _2 "2")
(def _3 "3")
(def _4 "4")
(def _5 "5")
(def _6 "6")
(def _7 "7")
(def _8 "8")
(def _9 "9")
(def _0 "0")
(def _hy "hyphen")
(def _eq "equal_sign")

; alphabetic glyphs
(def _a "a")
(def _b "b")
(def _c "c")
(def _d "d")
(def _e "e")
(def _f "f")
(def _g "g")
(def _h "h")
(def _i "i")
(def _j "j")
(def _k "k")
(def _l "l")
(def _m "m")
(def _n "n")
(def _o "o")
(def _p "p")
(def _q "q")
(def _r "r")
(def _s "s")
(def _t "t")
(def _u "u")
(def _v "v")
(def _w "w")
(def _x "x")
(def _y "y")
(def _z "z")

; function
(def _f1 "f1")
(def _f2 "f2")
(def _f3 "f3")
(def _f4 "f4")
(def _f5 "f5")
(def _f6 "f6")
(def _f7 "f7")
(def _f8 "f8")
(def _f9 "f9")
(def _f10 "f10")
(def _f11 "f11")
(def _f12 "f12")

(def _f13 "f13")
(def _f14 "f14")
(def _f15 "f15")
(def _f16 "f16")
(def _f17 "f17")
(def _f18 "f18")
(def _f19 "f19")
(def _f20 "f20")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; compound glyphs
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def kew_rs (mk K E W _rs))
(def keq_rs (mk K E Q _rs))
(def kewq_rs (mk K E W Q _rs))

(def kwq_ro (mk K W Q _ro))

(def kew_rc (mk K E W _rc))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
