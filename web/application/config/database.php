<?php defined('SYSPATH') OR die('No direct access allowed.');

return array
(
	'default' => array
	(
		'type'       => 'MySQL',
		'connection' => array(
			'hostname'   => 'localhost',
			'database'   => 'kohana',
			'username'   => FALSE,
			'password'   => FALSE,
			'persistent' => FALSE,
		),
		'table_prefix' => '',
		'charset'      => 'utf8',
		'caching'      => FALSE,
	),

	'idea' => array
	(
		'type'       => 'MySQL',
		'connection' => array(
			'hostname'   => 'localhost',
			'database'   => 'idea',
			'username'   => 'idea',
			'password'   => 'trinity1101',
			'persistent' => FALSE,
		),
		'table_prefix' => '',
		'charset'      => 'utf8',
		'caching'      => FALSE,
	),
);
