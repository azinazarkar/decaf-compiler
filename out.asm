	.data

	true_print_string: .asciiz "true"
	false_print_string: .asciiz "false"

	aa: .word 0
	ab: .word 0
	ac: .word 0
	_ad: .word 79
	_ae: .word 1078523331
	_af: .word 1078523331
	_ag: .word 1086911939

	.text
	.globl main

test:

	# Assigning _ad to ab
	lw $a0, _ad
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

	# Adding _ae and _af
	lwc1 $f0, _ae
	lwc1 $f1, _af
	add.s $f2, $f0, $f1
	la $a0, _ag
	swc1 $f2, 0($a0)
	
	# Assigning _ag to ac
	lwc1 $f0, _ag
	la $a0, ac
	swc1 $f0, 0($a0)
	
	# Exit!
	li $v0, 10
	syscall
	
	
