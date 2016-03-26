<?php defined('SYSPATH') OR die('No direct script access.');

abstract class Controller extends Kohana_Controller {

	public function __construct($request, $response)
	{
		parent::__construct($request, $response);
	}

	protected function _get_var($key, $default = NULL,$type='POST')
	{
		$value = NULL;
		$type = strtoupper($type);
		switch ($type) {
			case 'POST':
				// 当变量未曾被调用的第一次进行初始化
				if (!isset($this->self_data['POST']))
				{
					$this->self_data['POST'] = $_POST;
				}
				$value = Arr::get($this->self_data['POST'], $key,$default);
			break;
			default:
			break;
		}
		if ($value === '')
		{
			$value = $default;
		}
		return trim($value);
	}

	protected function _return_data($status,$content=NULL,$msg=NULL)
	{
		exit(json_encode(array('status'=>intval($status),'content'=>$content,'msg'=>__($msg))));
	}

	private $tpl_data = array();
	protected function assign($key,$value)
	{
		$this->tpl_data[$key] = $value;
		return $this;
	}

	protected function display($file=NULL)
	{
		if (empty($file))
		{
			$file=ucfirst($this->request->controller()).'/'.ucfirst($this->request->action());
		}
		$view = View::factory($file);
		foreach ($this->tpl_data as $key=>$value)
		{
			$view->$key = $value;
		}
		$this->response->body($view);
	}


}
