	.data

	true_print_string: .asciiz "true"
	false_print_string: .asciiz "false"

	aa: .word 0
	ab: .word 0
	ac: .word 0
	ad: .word 0
	_ae: .word 79
	_af: .word 1078523331
	_ag: .word 1078523331
	_ah: .word 1086911939
	_ai: .word 1084563128
	_aj: .word 1084542157
	_ak: .word 1008981504
	_al: .word 1078523331
	_am: .word -1078523331
	_an: .word 1069547520
	_ao: .word 1075838976
	_ap: .word 1081081856
	_aq: .word 1080033280
	_ar: .word -1080033280
	_as: .word 1056964608
	_at: .word -1056964608
	_au: .word 1042284544

	.text
	.globl main

test:

	# Assigning _ae to ab
	lw $a0, _ae
	la $a1, ab
	sw $a0, 0($a1)
	
	# Printing ab
	li $v0, 1
	lw $a0, ab
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
main:

	# Adding _af and _ag
	lwc1 $f0, _af
	lwc1 $f1, _ag
	add.s $f2, $f0, $f1
	la $a0, _ah
	swc1 $f2, 0($a0)
	
	# Assigning _ah to ac
	lwc1 $f0, _ah
	la $a0, ac
	swc1 $f0, 0($a0)
	
	# Printing ac
	li $v0, 2
	lwc1 $f12, ac
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Subtracting _aj from _ai
	lwc1 $f0, _ai
	lwc1 $f1, _aj
	sub.s $f2, $f0, $f1
	la $a0, _ak
	swc1 $f2, 0($a0)
	
	# Assigning _ak to ad
	lwc1 $f0, _ak
	la $a0, ad
	swc1 $f0, 0($a0)
	
	# Printing ad
	li $v0, 2
	lwc1 $f12, ad
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Getting negative value of _al
	lwc1 $f0, _al
	neg.s $f1, $f0
	swc1 $f1, _am
	
	# Printing _am
	li $v0, 2
	lwc1 $f12, _am
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Multiplying _an by _ao
	lwc1 $f0, _an
	lwc1 $f1, _ao
	mul.s $f2, $f0, $f1
	la $a0, _ap
	swc1 $f2, 0($a0)
	
	# Printing _ap
	li $v0, 2
	lwc1 $f12, _ap
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Getting negative value of _aq
	lwc1 $f0, _aq
	neg.s $f1, $f0
	swc1 $f1, _ar
	
	# Getting negative value of _as
	lwc1 $f0, _as
	neg.s $f1, $f0
	swc1 $f1, _at
	
	# Dividing _ar by _at
	lwc1 $f0, _ar
	lwc1 $f1, _at
	div.s $f2, $f0, $f1
	la $a0, _au
	swc1 $f2, 0($a0)
	
	# Printing _au
	li $v0, 2
	lwc1 $f12, _au
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Exit!
	li $v0, 10
	syscall
	
	
