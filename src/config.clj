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

(def pu "page_up")
(def pd "page_down")
(def hm "home")
(def ed "end")

(def kpu (mk "page_up"))
(def kpd (mk "page_down"))
(def khm (mk "home"))
(def ked (mk "end"))

(def kspu (mk bs "page_up"))
(def kspd (mk bs "page_down"))
(def kshm (mk bs "home"))
(def ksed (mk bs "end"))

(def au "up_arrow")
(def ad "down_arrow")
(def al "left_arrow")
(def ar "right_arrow")

(def kau (mk "up_arrow"))
(def kad (mk "down_arrow"))
(def kal (mk "left_arrow"))
(def kar (mk "right_arrow"))

(def ksau (mk bs "up_arrow"))
(def ksad (mk bs "down_arrow"))
(def ksal (mk bs "left_arrow"))
(def ksar (mk bs "right_arrow"))

(def koau (mk bo "up_arrow"))
(def koad (mk bo "down_arrow"))
(def koal (mk bo "left_arrow"))
(def koar (mk bo "right_arrow"))

(def kosau (mk bos "up_arrow"))
(def kosad (mk bos "down_arrow"))
(def kosal (mk bos "left_arrow"))
(def kosar (mk bos "right_arrow"))

(def ob "open_bracket")
(def cb "close_bracket")
(def sc "semicolon")
(def qu "quote")
(def bl "backslash")
(def cm "comma")
(def pe "period")
(def sl "slash")

(def kob (mk "open_bracket"))
(def kcb (mk "close_bracket"))
(def ksc (mk "semicolon"))
(def kqu (mk "quote"))
(def kbl (mk "backslash"))
(def kcm (mk "comma"))
(def kpe (mk "period"))
(def ksl (mk "slash"))

(def ksob (mk b s "open_bracket"))
(def kscb (mk b s "close_bracket"))
(def kssc (mk b s "semicolon"))
(def ksqu (mk b s "quote"))
(def ksbl (mk b s "backslash"))
(def kscm (mk b s "comma"))
(def kspe (mk b s "period"))
(def kssl (mk b s "slash"))

(def db "delete_or_backspace")
(def re "return_or_enter")
(def rs "right_shift")
(def ro "right_option")
(def rc "right_command")
(def sp "spacebar")

(def kdb (mk "delete_or_backspace"))
(def kre (mk "return_or_enter"))
(def krs (mk "right_shift"))
(def kro (mk "right_option"))
(def krc (mk "right_command"))
(def ksp (mk "spacebar"))

(def hy "hyphen")
(def eq "equal_sign")

(def khy (mk "hyphen"))
(def keq (mk "equal_sign"))

(def kshy (mk b s "hyphen"))
(def kseq (mk b s "equal_sign"))

(def rt "right_control")
(def lc "left_command")
(def lo "left_option")
(def lt "left_control")
(def ls "left_shift")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
