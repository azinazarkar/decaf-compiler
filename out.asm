	.data

	_true_print_string: .asciiz "true"
	_false_print_string: .asciiz "false"

	_dtoi_border_value: .word 0xbf000000

	aa: .word 0
	ab: .word 0
	_ac: .word 1072064102
	_ad: .word 0

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
	
_get_string_size:
	move $s0, $a0
	move $s1, $zero
_get_string_size_loop:
	lb $t2, 0($s0)
	beqz $t2, _get_string_size_end
	addi $s1, $s1, 1
	addi $s0, $s0, 1
	j _get_string_size_loop
_get_string_size_end:
	move $v0, $s1
	jr $ra
	
	
main:

	# Assigning _ac to aa
	lwc1 $f0, _ac
	la $a0, aa
	swc1 $f0, 0($a0)
	
	# dtoi is called for aa
	lwc1 $f0, aa
	lwc1 $f1, _dtoi_border_value
	c.lt.s $f0, $f1
	abs.s $f0, $f0
	round.w.s $f0, $f0
	mfc1 $s0, $f0
	bc1t _dtoi_isless_ae
	j _dtoi_end_af
_dtoi_isless_ae: 
	neg $s0, $s0
_dtoi_end_af: 
	la $s1, _ad
	sw $s0, 0($s1)
	
	# Assigning _ad to ab
	lw $a0, _ad
	la $a1, ab
	sw $a0, 0($a1)
	
	# Printing aa
	li $v0, 2
	lwc1 $f12, aa
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
	
	# Exit!
	li $v0, 10
	syscall
	
	
