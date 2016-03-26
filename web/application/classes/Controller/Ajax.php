<?php defined('SYSPATH') or die('No direct script access.');

class Controller_Ajax extends Controller {

	public function before()
	{
		parent::before();
	}

	public function action_index()
	{
		$this->response->body('hello, world!');
	}

	public function action_save_idea()
	{
		$title = $this->_get_var('title');
		$content = $this->_get_var('content');
		$nickname = $this->_get_var('nickname');
		$values = array(
			'title'	=> $title,
			'nickname'	=> $nickname,
			'text'	=> $content,
			'add_time'	=> date('Y-m-d H:i:s'),
		);
		Cookie::set('nickname',$nickname,604800);
		ORM::factory('Content')->values($values)->save();

	}

	public function action_save_gp()
	{
		$gp = $this->_get_var('gp');
		$idea_id = $this->_get_var('id');

		if (is_null($gp))
		{
			die('boom');
		}

			$idea = ORM::factory('Content',$idea_id);
			if ($idea->loaded())
			{
				$idea->add_comment($gp);
				$values = array(
					'idea_id'	=> $idea_id,
					'get_drop'	=> $gp,
					'ip'		=> Arr::get($_SERVER,'REMOTE_ADDR'),
					'add_time'	=> date("Y-m-d H:i:s"),
				);
				ORM::factory('Log')->values($values)->save();
			}
			echo 'ok';
	}
}
