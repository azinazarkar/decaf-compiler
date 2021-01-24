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
	_ai: .word -5
	_aj: .word 79
	_ak: .word -395

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

	# Getting negative value of _ah
	lw $s0, _ah
	neg $s1, $s0
	sw $s1, _ai
	
	# Assigning _ai to ac
	lw $a0, _ai
	la $a1, ac
	sw $a0, 0($a1)
	
	# Assigning _aj to ad
	lw $a0, _aj
	la $a1, ad
	sw $a0, 0($a1)
	
	# Multiplying ac by ad
	lw $a0, ac
	lw $a1, ad
	mult $a0, $a1
	la $a2, _ak
	mflo $t0
	sw $t0, 0($a2)
	
	# Assigning _ak to ae
	lw $a0, _ak
	la $a1, ae
	sw $a0, 0($a1)
	
	# Printing ac
	li $v0, 1
	lw $a0, ac
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Printing ad
	li $v0, 1
	lw $a0, ad
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Printing ae
	li $v0, 1
	lw $a0, ae
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Exit!
	li $v0, 10
	syscall
	
	
