	.data

	true_print_string: .asciiz "true"
	false_print_string: .asciiz "false"

	aa: .word 0
	ab: .word 0
	ac: .word 0
	ad: .word 0
	ae: .word 0
	af: .word 0
	_ag: .word 79
	_ah: .word 3
	_ai: .word 4
	_aj: .word 0
	_ak: .word 3
	_al: .word 3
	_am: .word 1
	_an: .word 1078523331
	_ao: .word 1078523331
	_ap: .word 1
	_aq: .word 1067702026
	_ar: .word 0

	.text
	.globl main

test:

	# Assigning _ag to ab
	lw $a0, _ag
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

	# Assigning _ah to ac
	lw $a0, _ah
	la $a1, ac
	sw $a0, 0($a1)
	
	# Assigning _ai to ad
	lw $a0, _ai
	la $a1, ad
	sw $a0, 0($a1)
	
	# Is ac equal to ad? 
	lw $a0, ac
	lw $a1, ad
	seq $t0, $a0, $a1
	la $a2, _aj
	sw $t0, 0($a2)
	
	# Printing _aj
	li $v0, 4
	la $a0, false_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Assigning _ak to ac
	lw $a0, _ak
	la $a1, ac
	sw $a0, 0($a1)
	
	# Assigning _al to ad
	lw $a0, _al
	la $a1, ad
	sw $a0, 0($a1)
	
	# Is ac equal to ad? 
	lw $a0, ac
	lw $a1, ad
	seq $t0, $a0, $a1
	la $a2, _am
	sw $t0, 0($a2)
	
	# Printing _am
	li $v0, 4
	la $a0, true_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Assigning _an to ae
	lwc1 $f0, _an
	la $a0, ae
	swc1 $f0, 0($a0)
	
	# Assigning _ao to af
	lwc1 $f0, _ao
	la $a0, af
	swc1 $f0, 0($a0)
	
	# Is ae equal to af? 
	lw $a0, ae
	lw $a1, af
	seq $t0, $a0, $a1
	la $a2, _ap
	sw $t0, 0($a2)
	
	# Printing _ap
	li $v0, 4
	la $a0, true_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Assigning _aq to ae
	lwc1 $f0, _aq
	la $a0, ae
	swc1 $f0, 0($a0)
	
	# Is ae equal to af? 
	lw $a0, ae
	lw $a1, af
	seq $t0, $a0, $a1
	la $a2, _ar
	sw $t0, 0($a2)
	
	# Printing _ar
	li $v0, 4
	la $a0, false_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Exit!
	li $v0, 10
	syscall
	
	
