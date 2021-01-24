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
	_ah: .word 5
	_ai: .word 5
	_aj: .word 1078523331
	_ak: .word 1082445005
	_al: .word -1082445005
	_am: .word 0
	_an: .word 1
	_ao: .word 0
	_ap: .word 1
	_aq: .word 1
	_ar: .word 1

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

	# Assigning _ah to ae
	lw $a0, _ah
	la $a1, ae
	sw $a0, 0($a1)
	
	# Assigning _ai to af
	lw $a0, _ai
	la $a1, af
	sw $a0, 0($a1)
	
	# Assigning _aj to ac
	lwc1 $f0, _aj
	la $a0, ac
	swc1 $f0, 0($a0)
	
	# Getting negative value of _ak
	lwc1 $f0, _ak
	neg.s $f1, $f0
	swc1 $f1, _al
	
	# Assigning _al to ad
	lwc1 $f0, _al
	la $a0, ad
	swc1 $f0, 0($a0)
	
	# Is ac less than ad? 
	lw $a0, ac
	lw $a1, ad
	slt $t0, $a0, $a1
	la $a2, _am
	sw $t0, 0($a2)
	
	# Printing _am
	li $v0, 4
	la $a0, false_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Is ad less than ac? 
	lw $a0, ad
	lw $a1, ac
	slt $t0, $a0, $a1
	la $a2, _an
	sw $t0, 0($a2)
	
	# Printing _an
	li $v0, 4
	la $a0, true_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Is ac less or equal to ad? 
	lw $a0, ac
	lw $a1, ad
	sle $t0, $a0, $a1
	la $a2, _ao
	sw $t0, 0($a2)
	
	# Printing _ao
	li $v0, 4
	la $a0, false_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Is ad less or equal to ac? 
	lw $a0, ad
	lw $a1, ac
	sle $t0, $a0, $a1
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
	
	# Is ae less or equal to af? 
	lw $a0, ae
	lw $a1, af
	sle $t0, $a0, $a1
	la $a2, _aq
	sw $t0, 0($a2)
	
	# Printing _aq
	li $v0, 4
	la $a0, true_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Is af less or equal to ae? 
	lw $a0, af
	lw $a1, ae
	sle $t0, $a0, $a1
	la $a2, _ar
	sw $t0, 0($a2)
	
	# Printing _ar
	li $v0, 4
	la $a0, true_print_string
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Exit!
	li $v0, 10
	syscall
	
	
