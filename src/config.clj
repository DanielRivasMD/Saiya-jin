;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; config
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns config)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn mk [& parts]
  (keyword (apply str parts)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;x

(def edn-path "edn/")

(def ay "alacritty")
(def br "broot")
(def mc "micro")
(def hc "helix-common")
(def hi "helix-insert")
(def hn "helix-normal")
(def hs "helix-select")
(def lg "lazygit")
(def tm "term")
(def ze  "zellij-enter")
(def zl  "zellij-lock")
(def zt  "zellij-rtab")
(def zp  "zellij-rpane")
(def zs  "zellij-view")
(def zj  "zellij-share")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def b "!")
(def p "#P")

(def o "O")
(def e "E")
(def t "T")
(def w "W")
(def c "C")
(def q "Q")
(def s "S")
(def r "R")

(def bs (str b s))

(def bo (str b o))
(def bos (str b o s))
(def bop (str bo p))
(def bosp (str bos p))

(def bt (str b t))
(def bts (str b t s))
(def btp (str bt p))
(def btsp (str bts p))

(def bw (str b w))
(def bws (str b w s))
(def bwp (str bw p))
(def bwsp (str bws p))

(def bc (str b c))
(def bcs (str b c s))
(def bcp (str bc p))
(def bcsp (str bcs p))

(def boc (str b o c))
(def bocs (str b o c s))
(def bocp (str b o c p))
(def bocsp (str b o c s p))

(def bot (str b o t))
(def bots (str b o t s))
(def botp (str b o t p))
(def botsp (str b o t s p))

(def btc (str b t c))
(def btcs (str b t c s))
(def btcp (str b t c p))
(def btcsp (str b t c s p))

(def botcp (str b o t c p))
(def botcsp (str b o t c s p))
(def botc (str b o t c))
(def botcs (str b o t c s))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; arrow glyphs
(def _au "up_arrow")
(def _ad "down_arrow")
(def _al "left_arrow")
(def _ar "right_arrow")

(def k_au (mk _au))
(def k_ad (mk _ad))
(def k_al (mk _al))
(def k_ar (mk _ar))

(def ks_au (mk bs _au))
(def ks_ad (mk bs _ad))
(def ks_al (mk bs _al))
(def ks_ar (mk bs _ar))

(def ko_au (mk bo _au))
(def ko_ad (mk bo _ad))
(def ko_al (mk bo _al))
(def ko_ar (mk bo _ar))

(def kos_au (mk bos _au))
(def kos_ad (mk bos _ad))
(def kos_al (mk bos _al))
(def kos_ar (mk bos _ar))

(def kt_au (mk bt _au))
(def kt_ad (mk bt _ad))
(def kt_al (mk bt _al))
(def kt_ar (mk bt _ar))

(def kts_au (mk bts _au))
(def kts_ad (mk bts _ad))
(def kts_al (mk bts _al))
(def kts_ar (mk bts _ar))

(def _pu "page_up")
(def _pd "page_down")
(def _hm "home")
(def _ed "end")

(def k_pu (mk _pu))
(def k_pd (mk _pd))
(def k_hm (mk _hm))
(def k_ed (mk _ed))

(def ks_pu (mk bs _pu))
(def ks_pd (mk bs _pd))
(def ks_hm (mk bs _hm))
(def ks_ed (mk bs _ed))

(def ko_pu (mk bo _pu))
(def ko_pd (mk bo _pd))
(def ko_hm (mk bo _hm))
(def ko_ed (mk bo _ed))

(def kos_pu (mk bos _pu))
(def kos_pd (mk bos _pd))
(def kos_hm (mk bos _hm))
(def kos_ed (mk bos _ed))

(def kt_pu (mk bt _pu))
(def kt_pd (mk bt _pd))
(def kt_hm (mk bt _hm))
(def kt_ed (mk bt _ed))

(def kts_pu (mk bts _pu))
(def kts_pd (mk bts _pd))
(def kts_hm (mk bts _hm))
(def kts_ed (mk bts _ed))

; technical glyphs
(def _ob "open_bracket")
(def _cb "close_bracket")
(def _sc "semicolon")
(def _qu "quote")
(def _bl "backslash")
(def _cm "comma")
(def _pe "period")
(def _sl "slash")

(def k_ob (mk _ob))
(def k_cb (mk _cb))
(def k_sc (mk _sc))
(def k_qu (mk _qu))
(def k_bl (mk _bl))
(def k_cm (mk _cm))
(def k_pe (mk _pe))
(def k_sl (mk _sl))

(def ks_ob (mk b s _ob))
(def ks_cb (mk b s _cb))
(def ks_sc (mk b s _sc))
(def ks_qu (mk b s _qu))
(def ks_bl (mk b s _bl))
(def ks_cm (mk b s _cm))
(def ks_pe (mk b s _pe))
(def ks_sl (mk b s _sl))

; action glyphs
(def _db "delete_or_backspace")
(def _re "return_or_enter")
(def _rs "right_shift")
(def _ro "right_option")
(def _rc "right_command")
(def _sp "spacebar")

(def k_db (mk _db))
(def k_re (mk _re))
(def k_rs (mk _rs))
(def k_ro (mk _ro))
(def k_rc (mk _rc))
(def k_sp (mk _sp))

; numeric-glyphs
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

(def k_hy (mk _hy))
(def k_eq (mk _eq))

(def ks_1 (mk bs _1))
(def ks_2 (mk bs _2))
(def ks_3 (mk bs _3))
(def ks_4 (mk bs _4))
(def ks_5 (mk bs _5))
(def ks_6 (mk bs _6))
(def ks_7 (mk bs _7))
(def ks_8 (mk bs _8))
(def ks_9 (mk bs _9))
(def ks_0 (mk bs _0))
(def ks_hy (mk b s _hy))
(def ks_eq (mk b s _eq))

; alphabetic-glyphs
(def _rt "right_control")
(def _lc "left_command")
(def _lo "left_option")
(def _lt "left_control")
(def _ls "left_shift")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
