	.data

	aa: .word 0
	ab: .word 0
	ac: .word 0
	ad: .word 0
	ae: .word 0
	_af: .word 5
	_ag: .word 10
	_ah: .word 15
	_ai: .word 5

	.text
	.globl main

main:

	lw $a0, _af
	la $a1, aa
	move $a2, $a0
	sw $a2, 0($a1)
	lw $a0, _ag
	la $a1, ab
	move $a2, $a0
	sw $a2, 0($a1)
	lw $a0, aa
	lw $a1, ab
	add $t0, $a0, $a1
	la $a2, _ah
	sw $t0, 0($a2)
	lw $a0, _ah
	la $a1, ac
	move $a2, $a0
	sw $a2, 0($a1)
	li $v0, 1
	lw $a0, aa
	syscall
	li $a0, 0xA
	li $v0, 0xB
	syscall
	li $v0, 1
	lw $a0, ab
	syscall
	li $a0, 0xA
	li $v0, 0xB
	syscall
	li $v0, 1
	lw $a0, ac
	syscall
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
test:

	lw $a0, _ai
	la $a1, ae
	move $a2, $a0
	sw $a2, 0($a1)
	
