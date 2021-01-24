	.data

	true_print_string: .asciiz "true"
	false_print_string: .asciiz "false"

	aa: .word 0
	ab: .word 0
	ac: .word 0
	ad: .word 0
	ae: .word 0
	_af: .word 5
	_ag: .word 12
	ah: .word -7
	_ai: .word 5

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
	
main:

	# Assigning _af to aa
	lw $a0, _af
	la $a1, aa
	move $a2, $a0
	sw $a2, 0($a1)
	
	# Assigning _ag to ab
	lw $a0, _ag
	la $a1, ab
	move $a2, $a0
	sw $a2, 0($a1)
	
	# Subtracting ab from aa
	lw $a0, aa
	lw $a1, ab
	sub $t0, $a0, $a1
	la $a2, ah
	sw $t0, 0($a2)
	
	# Assigning ah to ac
	lw $a0, ah
	la $a1, ac
	move $a2, $a0
	sw $a2, 0($a1)
	
	# Printing aa
	li $v0, 1
	lw $a0, aa
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Printing ab
	li $v0, 1
	lw $a0, ab
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Printing ac
	li $v0, 1
	lw $a0, ac
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	
test:

	# Assigning _ai to ae
	lw $a0, _ai
	la $a1, ae
	move $a2, $a0
	sw $a2, 0($a1)
	
	
