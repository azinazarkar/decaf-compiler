	.data

	true_print_string: .asciiz "true"
	false_print_string: .asciiz "false"

	aa: .word 0
	ab: .word 0
	ac: .word 0
	ad: .word 0
	ae: .word 0
	_af: .word 79
	_ag: .word 5
	_ah: .word -5
	_ai: .word 79
	_aj: .word -395

	.text
	.globl main

	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Exit!
	li $v0, 10
	syscall
	
test:

	# Assigning _af to ab
	lw $a0, _af
	la $a1, ab
	move $a2, $a0
	sw $a2, 0($a1)
	
	# Printing ab
	li $v0, 1
	lw $a0, ab
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	
main:

	# Getting negative value of _ag
	lw $s0, _ag
	neg $s1, $s0
	sw $s1, _ah
	
	# Assigning _ah to ac
	lw $a0, _ah
	la $a1, ac
	move $a2, $a0
	sw $a2, 0($a1)
	
	# Assigning _ai to ad
	lw $a0, _ai
	la $a1, ad
	move $a2, $a0
	sw $a2, 0($a1)
	
	# Multiplying ac by ad
	lw $a0, ac
	lw $a1, ad
	mult $a0, $a1
	la $a2, _aj
	mflo $t0
	sw $t0, 0($a2)
	
	# Assigning _aj to ae
	lw $a0, _aj
	la $a1, ae
	move $a2, $a0
	sw $a2, 0($a1)
	
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
	
	
