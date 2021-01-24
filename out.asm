	.data

	true_print_string: .asciiz "true"
	false_print_string: .asciiz "false"

	aa: .word 0
	ab: .word 0
	_ac: .word 79
	_ad: .word 0

	.text
	.globl main

test:

	# Assigning _ac to ab
	lw $a0, _ac
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

	# Reading an integer
	li $v0, 5
	syscall
	la $s0, _ad
	sw $v0, 0($s0)
	
	# Printing _ad
	li $v0, 1
	lw $a0, _ad
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Exit!
	li $v0, 10
	syscall
	
	
