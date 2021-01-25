	.data

	_true_print_string: .asciiz "true"
	_false_print_string: .asciiz "false"

	aa: .word 0
	ab: .word 0
	_ac: .word 3
	_ad: .word 4
	_ae: .word 0
	_af: .word 10
	_ag: .word 10
	_ah: .word 0

	.text
	.globl main

	
_print_true:
	li $v0, 4
	la $a0, _true_print_string
	syscall
	jr $ra
	
_print_false:
	li $v0, 4
	la $a0, _false_print_string
	syscall
	jr $ra
	
	
main:

	# Is _ac equal to _ad? 
	lw $a0, _ac
	lw $a1, _ad
	seq $t0, $a0, $a1
	la $a2, _ae
	sw $t0, 0($a2)
	
	# Assigning _ae to aa
	lw $a0, _ae
	la $a1, aa
	sw $a0, 0($a1)
	
	# Is _af equal to _ag? 
	lw $a0, _af
	lw $a1, _ag
	seq $t0, $a0, $a1
	la $a2, _ah
	sw $t0, 0($a2)
	
	# Assigning _ah to ab
	lw $a0, _ah
	la $a1, ab
	sw $a0, 0($a1)
	
	# Printing aa
	lw $s0, aa
	beq $s0, $zero, _print_false_ai
	bne $s0, $zero, _print_true_aj
_print_false_ai:
	sub $sp, $sp, 4
	sw $ra, 0($sp)
	jal _print_false
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	j _print_bool_end_ak
_print_true_aj:
	sub $sp, $sp, 4
	sw $ra, 0($sp)
	jal _print_true
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	j _print_bool_end_ak
_print_bool_end_ak: 
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Printing ab
	lw $s0, ab
	beq $s0, $zero, _print_false_al
	bne $s0, $zero, _print_true_am
_print_false_al:
	sub $sp, $sp, 4
	sw $ra, 0($sp)
	jal _print_false
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	j _print_bool_end_an
_print_true_am:
	sub $sp, $sp, 4
	sw $ra, 0($sp)
	jal _print_true
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	j _print_bool_end_an
_print_bool_end_an: 
	
	# Printing new line
	li $a0, 0xA
	li $v0, 0xB
	syscall
	
	# Exit!
	li $v0, 10
	syscall
	
	
