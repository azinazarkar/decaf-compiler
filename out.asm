	.data

	aa: .word 0
	ab: .word 0
	ac: .word 0
	_ad: .word 5
	_ae: .word 10
	_af: .word 15
	ag: .word 0
	ah: .word 0
	_ai: .word 5

	.text
	.globl main

main:

	lw $a0, _ad
	la $a1, aa
	move $a2, $a0
	sw $a2, 0($a1)
	lw $a0, _ae
	la $a1, ab
	move $a2, $a0
	sw $a2, 0($a1)
	lw $a0, aa
	lw $a1, ab
	add $t0, $a0, $a1
	la $a2, _af
	sw $t0, 0($a2)
	lw $a0, _af
	la $a1, ac
	move $a2, $a0
	sw $a2, 0($a1)
	
test:

	lw $a0, _ai
	la $a1, ah
	move $a2, $a0
	sw $a2, 0($a1)
	
