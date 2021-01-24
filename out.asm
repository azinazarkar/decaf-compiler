	.data

	true_print_string: .asciiz "true"
	false_print_string: .asciiz "false"

	aa: .word 0
	ab: .word 0
	ac: .word 0
	ad: .word 0
	ae: .word 0
	_af: .word 5
	_ag: .word -5
	_ah: .word 79
	_ai: .word -395
	_aj: .word 79

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
	
main:

	# Getting negative value of _af
	lw $s0, _af
	neg $s1, $s0
	sw $s1, _ag
	
	# Assigning _ag to aa
	lw $a0, _ag
	la $a1, aa
	move $a2, $a0
	sw $a2, 0($a1)
	
	# Assigning _ah to ab
	lw $a0, _ah
	la $a1, ab
	move $a2, $a0
	sw $a2, 0($a1)
	
	# Multiplying aa by ab
	lw $a0, aa
	lw $a1, ab
	mult $a0, $a1
	la $a2, _ai
	mflo $t0
	sw $t0, 0($a2)
	
	# Assigning _ai to ac
	lw $a0, _ai
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

	# Assigning _aj to ae
	lw $a0, _aj
	la $a1, ae
	move $a2, $a0
	sw $a2, 0($a1)
	
	# Printing ae
	li $v0, 1
	lw $a0, ae
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	
