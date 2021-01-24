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
	
	# Is ae not equal to af? 
	lw $a0, ae
	lw $a1, af
	sne $t0, $a0, $a1
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
	
	# Is ac not equal to ad? 
	lw $a0, ac
	lw $a1, ad
	sne $t0, $a0, $a1
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
	
	# Exit!
	li $v0, 10
	syscall
	
	
