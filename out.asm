	.data

	true_print_string: .asciiz "true"
	false_print_string: .asciiz "false"

	aa: .word 0
	ab: .word 0
	ac: .word 0
	ad: .word 0
	ae: .word 0
	af: .word 0
	_ag: .word 5
	_ah: .word 12
	ai: .word -7
	aj: .word 60
	_ak: .word 79

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
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
main:

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
	
	# Subtracting ab from aa
	lw $a0, aa
	lw $a1, ab
	sub $t0, $a0, $a1
	la $a2, ai
	sw $t0, 0($a2)
	
	# Assigning ai to ac
	lw $a0, ai
	la $a1, ac
	move $a2, $a0
	sw $a2, 0($a1)
	
	# Multiplying aa by ab
	lw $a0, aa
	lw $a1, ab
	mult $a0, $a1
	la $a2, aj
	mflo $t0
	sw $t0, 0($a2)
	
	# Assigning aj to ad
	lw $a0, aj
	la $a1, ad
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
	
	# Printing ad
	li $v0, 1
	lw $a0, ad
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	
test:

	# Assigning _ak to af
	lw $a0, _ak
	la $a1, af
	move $a2, $a0
	sw $a2, 0($a1)
	
	# Printing af
	li $v0, 1
	lw $a0, af
	syscall
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	
