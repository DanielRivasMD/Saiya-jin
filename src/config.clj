;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; config
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns config)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn mk [& parts]
  (keyword (apply str parts)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;x

(def edn-path "edn/")

(def br "broot")
(def mc "micro")
(def hc "helix-common")
(def hi "helix-insert")
(def hn "helix-normal")
(def hs "helix-select")
(def lg "lazygit")
(def tm "terminal")
(def zj  "zellij")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def b "!")
(def cap "#P")

(def o "O")
(def e "E")
(def t "T")
(def w "W")
(def s "S")
(def r "R")

(def bs (str b s))
(def hyp "OTC")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def ilctl (str b t cap))
(def ilctls (str b t s cap))
(def olctl (str b t))
(def olctls (str b t s))

(def ilopt (str b o cap))
(def ilopts (str b o s cap))
(def olopt (str b o))
(def olopts (str b o s))

(def ihyper (str b hyp cap))
(def ihypers (str b hyp s cap))
(def ohyper (str b hyp))
(def ohypers (str b hyp s))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def pu "page_up")
(def pd "page_down")
(def hm "home")
(def ed "end")

(def kpu (mk "page_up"))
(def kpd (mk "page_down"))
(def khm (mk "home"))
(def ked (mk "end"))

(def kspu (mk b s "page_up"))
(def kspd (mk b s "page_down"))
(def kshm (mk b s "home"))
(def ksed (mk b s "end"))

(def au "up_arrow")
(def ad "down_arrow")
(def al "left_arrow")
(def ar "right_arrow")

(def kau (mk "up_arrow"))
(def kad (mk "down_arrow"))
(def kal (mk "left_arrow"))
(def kar (mk "right_arrow"))

(def ksau (mk b s "up_arrow"))
(def ksad (mk b s "down_arrow"))
(def ksal (mk b s "left_arrow"))
(def ksar (mk b s "right_arrow"))

(def koau (mk b o "up_arrow"))
(def koad (mk b o "down_arrow"))
(def koal (mk b o "left_arrow"))
(def koar (mk b o "right_arrow"))

(def kosau (mk b o s "up_arrow"))
(def kosad (mk b o s "down_arrow"))
(def kosal (mk b o s "left_arrow"))
(def kosar (mk b o s "right_arrow"))

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
